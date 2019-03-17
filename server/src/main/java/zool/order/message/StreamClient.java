package zool.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author：zoolye
 * @date：2019-03-16：22:57
 * @description：
 */
public interface StreamClient {

    String input = "orderStreamMessage";
    String output = "streamMessage";

    @Input(StreamClient.input)
    SubscribableChannel input();

    @Output(StreamClient.output)
    MessageChannel output();


}
