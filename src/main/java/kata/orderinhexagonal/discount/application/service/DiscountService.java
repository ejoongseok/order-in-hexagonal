package kata.orderinhexagonal.discount.application.service;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.item.domain.Item;

public class DiscountService implements CreateItemDiscountUsecase {

	private final DiscountItemLoadPort discountItemLoadPort = new DiscountItemLoadPort();

	@Override
	public Discount createItemDiscount(CreateItemDiscountRequest request) {
		Item discountItem = discountItemLoadPort.load(request.getItemId());
		Discount discount = new Discount(request.getDiscountType(), request.getDiscountRate(), discountItem);
		return discount;
	}
}
