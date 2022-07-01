package kata.orderinhexagonal.stock.adapter.out.web;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.domain.Item;

public interface StockItemNetworkClient {
	Item findItemById(Long id);

	ItemEntity findItemEntityById(Long id);
}
