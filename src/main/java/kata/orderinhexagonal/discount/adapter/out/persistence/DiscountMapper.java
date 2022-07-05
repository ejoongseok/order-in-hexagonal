package kata.orderinhexagonal.discount.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.domain.Discount;

@Component
public class DiscountMapper {

	public DiscountEntity toEntity(Discount discount) {
		return new DiscountEntity(discount.getId(), discount.getDiscountType(),discount.getItem(), discount.getDiscountValue());
	}
}
