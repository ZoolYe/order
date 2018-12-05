package zool.order.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zool.order.dataobject.OrderDetail;
import zool.order.dataobject.OrderMaster;
import zool.order.dataobject.ProductInfo;
import zool.order.dto.CartDto;
import zool.order.enums.OrderStatusEnum;
import zool.order.enums.PayStatusEnum;
import zool.order.dto.OrderDto;
import zool.order.repository.OrderDetailRepository;
import zool.order.repository.OrderMasterRepository;
import zool.order.service.OrderService;
import zool.order.utils.KeyUtil;
import zool.product.client.ProductClient;
import zool.product.common.DecreaseStockInput;
import zool.product.common.ProductInfoOutput;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private ProductClient productClient;

    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto create(OrderDto orderDto) {

        //生成订单id
        String orderId = KeyUtil.genUniqueKey();

//        2. 查询商品信息(调用商品服务)
        List<String> productIdList = orderDto.getOrderDetailList().stream()
                .map(OrderDetail::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

//        3. 计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {

            for (ProductInfoOutput productInfo : productInfoList) {
                if (productInfo.getProductId().equals(orderDetail.getProductId())) {
                    //总价：单价乘以数量
                    orderAmout = (productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity())))
                            .add(orderAmout);
                    BeanUtils.copyProperties(productInfo,orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

//        4. 扣库存(调用商品服务)
        List<DecreaseStockInput> decreaseStockInputList = orderDto.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputList);

//        5. 订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderDto;
    }
}
