package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentRepository;
import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.Payment;
import kata.orderinhexagonal.payment.domain.PaymentType;

@Component
public class PaymentFixture {

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	PaymentUsecase paymentUsecase;

	public void clearPayment() {
		paymentRepository.deleteAll();
	}

	public Payment createPayment(Long orderId) {
		PaymentRequest request = PaymentRequest.of(orderId, CardType.CREDIT_CARD, CardCompany.KATA, "1234567890123456",
			"123", PaymentType.PAY_IN_FULL);
		return paymentUsecase.payments(request);
	}

	public PaymentEntity getPaymentEntity(Long id) {
		PaymentEntity paymentEntity = paymentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Payment not found"));
		return paymentEntity;
	}
}
