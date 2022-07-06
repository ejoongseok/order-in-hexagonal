package kata.orderinhexagonal.payment.application.port.out;

import kata.orderinhexagonal.payment.domain.PaymentStatus;
import kata.orderinhexagonal.payment.schedule.RefundRequest;

public interface RequestPayPort {

	PaymentStatus pay(RequestPay requestPay);

	void refund(RefundRequest request);
}
