package zool.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zool.order.dataobject.OrderMaster;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
