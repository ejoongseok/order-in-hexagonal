package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemOrderStockNetworkMonolithicClient implements ItemOrderStockNetworkClient {
	private final ApplicationEventPublisher eventPublisher;

	@Override
	public void stockOut(Item item, int stockOutQuantity) {
		eventPublisher.publishEvent(new OrderItemStockOutEvent(item, stockOutQuantity));
	}
}
