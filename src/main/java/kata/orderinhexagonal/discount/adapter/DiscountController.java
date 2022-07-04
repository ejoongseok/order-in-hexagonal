package kata.orderinhexagonal.discount.adapter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountResponse;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

	@PostMapping("/items")
	@ResponseStatus(HttpStatus.CREATED)
	public CreateItemDiscountResponse createDiscount(@RequestBody CreateItemDiscountRequest request) {
		return null;
	}
}
