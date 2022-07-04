package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.item.domain.Item;

public interface ItemOrderStockOutPort {
	void stockOut(Item item, int stockOutQuantity);
}
