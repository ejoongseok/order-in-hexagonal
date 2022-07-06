package kata.orderinhexagonal.payment.adapter.out.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

	@Query("select p from PaymentEntity p where p.order.id = ?1")
	Optional<PaymentEntity> findByOrderId(Long orderId);
}
