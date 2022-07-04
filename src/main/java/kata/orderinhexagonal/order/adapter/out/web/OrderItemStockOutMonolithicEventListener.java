package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderItemStockOutMonolithicEventListener {

	private final StockOutUsecase stockOutUsecase;

	@Async
	@EventListener
	public void handle(OrderItemStockOutEvent event) {
		Item item = event.getItem();
		item.stockOutQuantity(event.getStockOutQuantity());
		stockOutUsecase.stockOut(StockOutRequest.of(item.getId(), event.getStockOutQuantity()));
	}
}
