package zool.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import zool.order.dto.OrderDto;

/**
 * @author：zoolye
 * @date：2019-03-16：22:59
 * @description：
 */
@Slf4j
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.input)
////    public void orderStreamMessageProcess(Object message) {
////        log.info("OrderStreamMessage: {}", message);
////    }

    @StreamListener(StreamClient.output)
    public void streamMessageProcess(Object message) {
        log.info("StreamMessage: {}", message);
    }

    @StreamListener(StreamClient.input)
    public void orderStreamMessageProcess(OrderDto message) {
        log.info("OrderStreamMessage: {}", message);
    }

}
