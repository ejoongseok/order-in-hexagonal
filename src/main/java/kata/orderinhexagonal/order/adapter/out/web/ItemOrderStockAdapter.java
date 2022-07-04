package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemOrderStockAdapter implements ItemOrderStockOutPort {

	private final ItemOrderStockNetworkClient itemOrderStockNetworkClient;

	@Override
	public void stockOut(Item item, int stockOutQuantity) {
		itemOrderStockNetworkClient.stockOut(item, stockOutQuantity);
	}
}
