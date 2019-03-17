package zool.order.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zool.order.dto.OrderDto;
import zool.order.message.StreamClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author：zoolye
 * @date：2019-03-16：23:03
 * @description：
 */
@RestController
@RequestMapping("/send/message")
public class SendMessageController {

    @Autowired
    StreamClient streamClient;

    @PostMapping("/stream")
    public void sendMessageByStream() {
        streamClient.input().send(MessageBuilder
                .withPayload("测试Stream发送消息：时间：" + LocalDateTime.now()).build());
    }

    @PostMapping("/order")
    public void sendMessageByOrder() {
        OrderDto orderDto = OrderDto.builder()
                .orderId("123456")
                .buyerName("zoolye")
                .buyerPhone("12345678910")
                .buyerAddress("杭州")
                .buyerOpenid("zoolye")
                .orderAmount(BigDecimal.valueOf(13000))
                .orderStatus(0)
                .payStatus(0)
                .build();
        streamClient.input().send(MessageBuilder.withPayload(orderDto).build());
    }

}

