package kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;

@Component
public class ItemMapper {

	ItemEntity toEntity(Item domain) {
		return new ItemEntity(domain.getName(), domain.getPrice(), domain.getStockQuantity());
	}
}
