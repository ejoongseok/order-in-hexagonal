package kata.orderinhexagonal.delivery.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDeliveryRepository extends JpaRepository<OrderDeliveryEntity, Long> {
	List<OrderDeliveryEntity> findByOrderId(Long orderId);
}
