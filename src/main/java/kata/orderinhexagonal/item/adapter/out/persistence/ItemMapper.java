package kata.orderinhexagonal.item.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.adapter.out.persistence.DiscountMapper;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Component
public class ItemMapper {


	public ItemEntity toEntity(Item domain) {
		return new ItemEntity(domain.getId(), domain.getName(), domain.getPrice(), domain.getStockQuantity(), domain.getDiscount());
	}
	public Item toDomain(ItemEntity entity) {
		return new Item(entity.getId(), entity.getName(), entity.getPrice(), entity.getStockQuantity(), entity.getDiscount());
	}
}
