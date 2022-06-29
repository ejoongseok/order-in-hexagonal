package kata.orderinhexagonal.item.application.port.out;

import kata.orderinhexagonal.item.domain.Item;

public interface SaveItemPort {
	void saveItem(Item item);
}
