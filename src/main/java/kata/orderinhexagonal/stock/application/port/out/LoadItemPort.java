package kata.orderinhexagonal.stock.application.port.out;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.domain.Item;

public interface LoadItemPort {
	Item load(Long id);

	ItemEntity loadEntity(Long id);
}
