package kata.orderinhexagonal.discount.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DiscountMapper {

	private final ItemMapper itemMapper;

	public DiscountEntity toEntity(Discount discount) {
		return new DiscountEntity(discount.getId(), discount.getDiscountType(),itemMapper.toEntity(discount.getItem()), discount.getDiscountValue());
	}
}
