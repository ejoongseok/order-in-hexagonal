package kata.orderinhexagonal.payment.schedule;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentRepository;
import kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import kata.orderinhexagonal.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentCancellationScheduler {

	private final PaymentRepository paymentRepository;
	private final RequestPayPort requestPayPort;

	@Scheduled(fixedDelay = 10000)
	public void cancelPaymentRequests() {
		List<PaymentEntity> paymentEntities = paymentRepository.findByCancellationStatus(
			PaymentStatus.CANCELLATION_REQUEST);
		for (PaymentEntity paymentEntity : paymentEntities) {
			RefundRequest request = new RefundRequest(paymentEntity.getCardType(), paymentEntity.getCardCompany(), paymentEntity.getPaymentType(), paymentEntity.getCardNumber(), paymentEntity.getCardCvc(), paymentEntity.getPaymentPrice());
			requestPayPort.refund(request);
			paymentEntity.cancel();
		}
	}
}
