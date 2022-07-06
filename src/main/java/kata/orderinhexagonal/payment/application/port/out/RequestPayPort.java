package kata.orderinhexagonal.payment.application.port.out;

import kata.orderinhexagonal.payment.domain.PaymentStatus;

public interface RequestPayPort {

	PaymentStatus pay(RequestPay requestPay);
}
