package kata.orderinhexagonal.stock.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadItemAdapter implements LoadItemPort {

	private final StockItemNetworkClient stockItemNetworkClient;

	@Override
	public Item load(Long id) {
		return stockItemNetworkClient.findItemById(id);
	}

	@Override
	public ItemEntity loadEntity(Long id) {
		return stockItemNetworkClient.findItemEntityById(id);
	}
}
