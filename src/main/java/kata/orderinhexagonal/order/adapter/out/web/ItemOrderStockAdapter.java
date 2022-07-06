package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.application.port.out.CancelStockOutItemPort;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemOrderStockAdapter implements ItemOrderStockOutPort, CancelStockOutItemPort {

	private final ItemOrderStockNetworkClient itemOrderStockNetworkClient;

	@Override
	public void stockOut(Item item, int stockOutQuantity) {
		itemOrderStockNetworkClient.stockOut(item, stockOutQuantity);
	}

	@Override
	public void cancelStockOutItem(Item cancelOrderItem, int orderQuantity) {
		itemOrderStockNetworkClient.stockIn(cancelOrderItem, orderQuantity);
	}
}
