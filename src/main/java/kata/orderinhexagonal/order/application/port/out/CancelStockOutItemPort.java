package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.domain.Stock;

public interface CancelStockOutItemPort {
	Stock cancelStockOutItem(Item cancelOrderItem, int orderQuantity);
}
