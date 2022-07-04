package kata.orderinhexagonal.discount.application.port.out;

import kata.orderinhexagonal.item.domain.Item;

public interface DiscountItemLoadPort {
	Item load(Long itemId);
}
