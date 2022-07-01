package kata.orderinhexagonal.order.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public CreateOrderResponse createOrder(@RequestBody @Valid CreateOrderRequest request) {
		return null;
	}
}
