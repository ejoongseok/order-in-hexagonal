package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.Getter;

@Getter
public class OrderItemStockOutEvent {

	private Item item;
	private int stockOutQuantity;

	public OrderItemStockOutEvent(Item item, int stockOutQuantity) {
		this.item = item;
		this.stockOutQuantity = stockOutQuantity;
	}
}
