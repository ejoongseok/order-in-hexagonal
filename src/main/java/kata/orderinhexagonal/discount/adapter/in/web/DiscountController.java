package kata.orderinhexagonal.discount.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountResponse;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import kata.orderinhexagonal.discount.domain.Discount;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {

	private final CreateItemDiscountUsecase createItemDiscountUsecase;

	@PostMapping("/items")
	@ResponseStatus(HttpStatus.CREATED)
	public CreateItemDiscountResponse createDiscount(@RequestBody CreateItemDiscountRequest request) {
		Discount itemDiscount = createItemDiscountUsecase.createItemDiscount(request);
		return new CreateItemDiscountResponse(itemDiscount.getId(), itemDiscount.getItem().getId(), itemDiscount.getDiscountType(), itemDiscount.getDiscountValue());
	}
}
