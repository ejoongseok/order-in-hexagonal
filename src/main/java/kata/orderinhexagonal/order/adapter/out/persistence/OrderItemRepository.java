package kata.orderinhexagonal.order.adapter.out.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

	@Query("select oi from OrderItemEntity oi join fetch oi.item join fetch oi.order where oi.order.id = ?1")
	List<OrderItemEntity> findByOrderId(Long orderId);
}
