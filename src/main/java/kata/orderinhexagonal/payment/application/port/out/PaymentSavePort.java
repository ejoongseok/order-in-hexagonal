package kata.orderinhexagonal.payment.application.port.out;

import kata.orderinhexagonal.payment.domain.Payment;

public interface PaymentSavePort {
	void save(Payment payment);
}
