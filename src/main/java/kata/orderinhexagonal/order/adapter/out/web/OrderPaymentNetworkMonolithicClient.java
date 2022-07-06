package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderPaymentNetworkMonolithicClient implements OrderPaymentNetworkClient {

	private final PaymentRepository paymentRepository;

	@Override
	@Transactional
	public void cancelPaymentRequest(Long id) {
		PaymentEntity paymentEntity = paymentRepository.findPaymentWithOrderByOrderId(id)
			.orElseThrow(() -> new IllegalArgumentException("Payment not found"));

		paymentEntity.paymentCancellation();

	}
}
