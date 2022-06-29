package kata.orderinhexagonal.item.adapter.out.persistence;

import kata.orderinhexagonal.item.domain.Item;

public interface ItemRepository {
	void save(Item item);
}
