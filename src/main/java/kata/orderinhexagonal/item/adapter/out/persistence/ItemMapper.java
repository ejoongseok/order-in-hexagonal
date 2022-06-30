package kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;

@Component
public class ItemMapper {

	public ItemEntity toEntity(Item domain) {
		return new ItemEntity(domain.getId(), domain.getName(), domain.getPrice(), domain.getStockQuantity());
	}
	public Item toDomain(ItemEntity entity) {
		return new Item(entity.getId(), entity.getName(), entity.getPrice(), entity.getStockQuantity());
	}
}
