package kata.orderinhexagonal.payment.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.application.port.in.PaymentResponse;
import kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import kata.orderinhexagonal.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentUsecase paymentUsecase;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest paymentRequest, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}

		Payment payments = paymentUsecase.payments(paymentRequest);

		return new PaymentResponse(payments.getId(), payments.getOrder().getId(), payments.getOrder().getTotalPrice(), payments.getPaymentType(), payments.getCardType());
	}
}
