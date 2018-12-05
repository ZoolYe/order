package zool.order.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zool.order.OrderApplicationTests;
import zool.order.dataobject.OrderMaster;
import zool.order.enums.OrderStatusEnum;
import zool.order.enums.PayStatusEnum;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){

        OrderMaster orderMaster = OrderMaster.builder()
                .orderId("1234567")
                .buyerName("师兄")
                .buyerPhone("18888888888")
                .buyerAddress("杭州阿里巴巴")
                .buyerOpenid("alibaba")
                .orderAmount(new BigDecimal(2.5))
                .orderStatus(OrderStatusEnum.NEW.getCode())
                .payStatus(PayStatusEnum.WAIT.getCode()).build();

        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}