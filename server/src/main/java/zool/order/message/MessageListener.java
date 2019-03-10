package zool.order.message;

import lombok.extern.slf4j.Slf4j;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author：zoolye
 * @date：2019-03-10：21:52
 * @description：
 */
@Slf4j
@Component
public class MessageListener {

    @RabbitListener(bindings = @QueueBinding
            (value = @Queue(value = "order-queue", durable = "true"),
                    exchange = @Exchange(value = "order-exchange", type = "topic"),
                    key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(Message message, @Headers Map<String, Object> headers, Channel channel) {
        log.info("开始消费消息 Body :{}", message.getPayload());
    }

}
