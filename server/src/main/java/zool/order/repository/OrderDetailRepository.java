package zool.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zool.order.dataobject.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
}
