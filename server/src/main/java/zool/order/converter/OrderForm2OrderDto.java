package zool.order.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import zool.order.dataobject.OrderDetail;
import zool.order.dto.OrderDto;
import zool.order.enums.ResultEnum;
import zool.order.exception.OrderException;
import zool.order.from.OrderFrom;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDto {

    public static OrderDto convert(OrderFrom orderFrom){
        OrderDto orderDto = OrderDto.builder()
                .buyerName(orderFrom.getName())
                .buyerPhone(orderFrom.getPhone())
                .buyerAddress(orderFrom.getAddress())
                .buyerOpenid(orderFrom.getOpenid()).build();

        List<OrderDetail> orderDetailList = new ArrayList<>();

        Gson gson = new Gson();

        try {
            orderDetailList = gson.fromJson(orderFrom.getItems(),
                    new TypeToken<List<OrderDetail>> (){}.getType());

        }catch (Exception e){
            log.error("【json转换】错误, string={}",orderFrom.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }

        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
