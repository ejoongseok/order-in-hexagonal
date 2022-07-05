package kata.orderinhexagonal.order.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	@Query("select o from OrderEntity o join fetch o.member where o.id = ?1")
	Optional<OrderEntity> findOrderWithMemberById(Long orderId);
}
