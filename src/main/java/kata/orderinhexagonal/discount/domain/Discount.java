package kata.orderinhexagonal.discount.domain;

import kata.orderinhexagonal.item.domain.Item;

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

	public long getId() {
		return id;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public Item getItem() {
		return item;
	}

	public int getDiscountValue() {
		return discountValue;
	}
}
