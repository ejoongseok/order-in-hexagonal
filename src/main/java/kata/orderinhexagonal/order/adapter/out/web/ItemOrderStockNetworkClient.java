package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;

public interface ItemOrderStockNetworkClient {
	void stockOut(Item item, int stockOutQuantity);
}
