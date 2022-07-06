package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentRepository;

@Component
public class PaymentFixture {

	@Autowired
	private PaymentRepository paymentRepository;

	public void clearPayment() {
		paymentRepository.deleteAll();
	}

	public PaymentEntity getPaymentEntity(Long id) {
		PaymentEntity paymentEntity = paymentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Payment not found"));
		return paymentEntity;
	}
}
