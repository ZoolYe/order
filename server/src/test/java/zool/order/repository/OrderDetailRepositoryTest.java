package zool.order.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import zool.order.OrderApplicationTests;
import zool.order.dataobject.OrderDetail;

import java.math.BigDecimal;


@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void testSave(){

        OrderDetail orderDetail = OrderDetail.builder()
                .detailId("12222")
                .orderId("331231")
                .productIcon("http://www.zoolye.com")
                .productId("157875196366160022")
                .productName("皮蛋瘦肉粥")
                .productPrice(new BigDecimal(0.01))
                .productQuantity(2).build();

        OrderDetail result = orderDetailRepository.save(orderDetail);

        Assert.assertTrue(result != null);

    }

}