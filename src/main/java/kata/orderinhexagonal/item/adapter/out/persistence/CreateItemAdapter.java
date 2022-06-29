package kata.orderinhexagonal.item.adapter.out.persistence;

import kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import kata.orderinhexagonal.item.domain.Item;

public class CreateItemAdapter implements SaveItemPort {
	ItemRepository itemRepository = new MemoryItemRepository();

	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
}
