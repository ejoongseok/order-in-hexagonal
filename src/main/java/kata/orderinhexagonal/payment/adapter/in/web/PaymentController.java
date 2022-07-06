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

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest paymentRequest, Errors errors) {
		return null;
	}
}
