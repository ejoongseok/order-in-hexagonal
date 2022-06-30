package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.service.ItemService;
import kata.orderinhexagonal.item.domain.Item;

@Component
public class ItemFixture {

	@Autowired ItemService itemService;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	ItemMapper itemMapper;

	public Item createItem(String name, int price) {
		return itemService.createItem(CreateItemRequest.of(name, price));
	}

	public Item getItem(Long id) {
		ItemEntity itemEntity = itemRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Item not found"));
		return itemMapper.toDomain(itemEntity);
	}
}
