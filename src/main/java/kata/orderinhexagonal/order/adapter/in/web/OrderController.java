package kata.orderinhexagonal.order.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.auth.JwtProvider;
import kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CancelOrderResponse;
import kata.orderinhexagonal.order.application.port.in.CancelOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderResponse;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

	private final JwtProvider jwtProvider;

	private final CreateOrderUsecase createOrderUsecase;

	private final CancelOrderUsecase cancelOrderUsecase;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public CreateOrderResponse createOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody @Valid CreateOrderRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}
		Long memberId = jwtProvider.parseToken(authorization.substring(7));
		request.assignOrdererId(memberId);

		Order order = createOrderUsecase.createOrder(request);

		return new CreateOrderResponse(order);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.OK)
	public CancelOrderResponse cancelOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody CancelOrderRequest request) {
		Long memberId = jwtProvider.parseToken(authorization.substring(7));
		request.assignOrdererId(memberId);

		Order cancelOrder = cancelOrderUsecase.cancelOrder(request);
		return new CancelOrderResponse(cancelOrder);
	}
}
