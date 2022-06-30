package kata.orderinhexagonal.stock.adapter.out.web;

import java.util.Optional;

import kata.orderinhexagonal.item.domain.Item;

public interface StockItemNetworkClient {
	Optional<Item> findItemById(Long id);
}
