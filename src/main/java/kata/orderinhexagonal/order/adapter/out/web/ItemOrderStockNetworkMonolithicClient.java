package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemOrderStockNetworkMonolithicClient implements ItemOrderStockNetworkClient {
	private final ApplicationEventPublisher eventPublisher;

	@Override
	public void stockOut(Item item, int stockOutQuantity) {
		item.stockOutQuantity(stockOutQuantity);
		eventPublisher.publishEvent(new OrderItemStockOutEvent(StockOutRequest.of(item.getId(), stockOutQuantity)));
	}
}
