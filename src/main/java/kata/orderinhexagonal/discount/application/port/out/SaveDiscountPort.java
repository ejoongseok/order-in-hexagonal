package kata.orderinhexagonal.discount.application.port.out;

import kata.orderinhexagonal.discount.domain.Discount;

public interface SaveDiscountPort {
	void save(Discount discount);
}
