package kata.orderinhexagonal.payment.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import kata.orderinhexagonal.payment.domain.Payment;

@Service
public class PaymentService implements PaymentUsecase {
	@Override
	public Payment payments(PaymentRequest request) {
		return null;
	}
}
