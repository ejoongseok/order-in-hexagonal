package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import lombok.Getter;

@Getter
public class CancelOrderItemStockRollbackEvent {
	private final StockInRequest stockInRequest;
	public CancelOrderItemStockRollbackEvent(StockInRequest request) {
		this.stockInRequest = request;
	}
}
