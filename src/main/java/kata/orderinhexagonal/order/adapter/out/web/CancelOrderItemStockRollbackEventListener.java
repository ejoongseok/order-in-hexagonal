package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancelOrderItemStockRollbackEventListener {
	private final StockInUsecase stockInUsecase;

	@Async
	@EventListener
	public void rollbackStocks(CancelOrderItemStockRollbackEvent event) {
		stockInUsecase.stockIn(event.getStockInRequest());
	}
}
