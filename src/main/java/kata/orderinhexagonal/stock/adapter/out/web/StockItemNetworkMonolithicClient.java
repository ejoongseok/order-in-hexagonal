package kata.orderinhexagonal.stock.adapter.out.web;

import java.util.Optional;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import kata.orderinhexagonal.item.domain.Item;

public class StockItemNetworkMonolithicClient implements StockItemNetworkClient {
	ItemRepository itemRepository;
	ItemMapper itemMapper;

	@Override
	public Item findItemById(Long id) {
		ItemEntity itemEntity = itemRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Item not found"));
		Item item = itemMapper.toDomain(itemEntity);
		return item;

	}
}
