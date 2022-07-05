package kata.orderinhexagonal.discount.domain;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
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

	public Discount(Long id, Item toDomain, DiscountType discountType, int discountValue) {
		this.id = id;
		this.discountType = discountType;
		this.item = toDomain;
		this.discountValue = discountValue;
	}

	public Discount(Long id, ItemEntity entity, DiscountType discountType, int discountValue) {
		this.id = id;
		this.discountType = discountType;
		this.item = new Item(entity.getId(), entity.getName(),entity.getPrice(), entity.getStockQuantity(), this);
		this.discountValue = discountValue;
	}

	public void assignId(Long id) {
		this.id = id;
	}

	public int discountPrice(int orderPrice) {
		int result = 0;
		if (discountType == DiscountType.RATE) {
			result = orderPrice - (orderPrice * discountValue / 100);
		}
		if (discountType == DiscountType.FIXED) {
			result = orderPrice - discountValue;
		}

		return result < 0 ? 0 : result;

	}
}
