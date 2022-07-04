package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.Discount;

public interface CreateItemDiscountUsecase {
	Discount createItemDiscount(CreateItemDiscountRequest request);
}
