package kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateItemAdapter implements SaveItemPort {
	private final ItemRepository itemRepository;

	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
	}
}
