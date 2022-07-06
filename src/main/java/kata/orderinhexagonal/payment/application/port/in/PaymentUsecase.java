package kata.orderinhexagonal.payment.application.port.in;

import kata.orderinhexagonal.payment.domain.Payment;

public interface PaymentUsecase {
	Payment payments(PaymentRequest request);
}
