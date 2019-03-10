package zool.order.message;

import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zool.order.OrderApplicationTests;

/**
 * @author：zoolye
 * @date：2019-03-10：21:55
 * @description：
 */
@Component
public class MessageTest extends OrderApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() {
        rabbitTemplate.convertAndSend("order-exchange",
                "order.abc", "message content");
    }

}
