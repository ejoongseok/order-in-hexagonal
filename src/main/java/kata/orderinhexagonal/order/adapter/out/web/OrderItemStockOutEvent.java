package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.Getter;

@Getter
public class OrderItemStockOutEvent {
	private final StockOutRequest stockOutRequest;
	public OrderItemStockOutEvent(StockOutRequest stockOutRequest) {
		this.stockOutRequest = stockOutRequest;
	}
}
