package kata.orderinhexagonal.discount.domain;

import kata.orderinhexagonal.item.domain.Item;
import lombok.Getter;

@Getter
public class Discount {

	private Long id;
	private DiscountType discountType;
	private Item item;
	private int discountValue;

	public Discount(DiscountType discountType, int discountRate, Item discountItem) {
		this.discountType = discountType;
		this.discountValue = discountRate;
		this.item = discountItem;
	}

	public void assignId(Long id) {
		this.id = id;
	}
}
