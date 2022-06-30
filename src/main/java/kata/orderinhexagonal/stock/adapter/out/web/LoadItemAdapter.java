package kata.orderinhexagonal.stock.adapter.out.web;

import java.util.Optional;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;

public class LoadItemAdapter implements LoadItemPort {

	StockItemNetworkClient stockItemNetworkClient;

	@Override
	public Item load(Long id) {
		return stockItemNetworkClient.findItemById(id)
			.orElseThrow(() -> new RuntimeException("Item not found"));
	}
}
