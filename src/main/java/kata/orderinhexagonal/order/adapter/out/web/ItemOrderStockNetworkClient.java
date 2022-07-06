package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.domain.Stock;

public interface ItemOrderStockNetworkClient {
	void stockOut(Item item, int stockOutQuantity);

	void stockIn(Item cancelOrderItem, int orderQuantity);
}
