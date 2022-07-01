package kata.orderinhexagonal.order.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.auth.JwtProvider;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final JwtProvider jwtProvider;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public CreateOrderResponse createOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody @Valid CreateOrderRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}
		Long memberId = jwtProvider.parseToken(authorization.substring(7));
		request.assignOrdererId(memberId);

		return new CreateOrderResponse();
	}
}
