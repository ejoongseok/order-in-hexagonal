package kata.orderinhexagonal.discount.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;

public interface DiscountItemNetworkClient {
	Item loadDiscountItem(Long itemId);
}
