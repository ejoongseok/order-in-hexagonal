package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.DiscountType;

public class CreateItemDiscountRequest {
	private Long itemId;
	private DiscountType discountType;
	private int discountRate;

	public CreateItemDiscountRequest(Long itemId, DiscountType discountType, int discountRate) {
		this.itemId = itemId;
		this.discountType = discountType;
		this.discountRate = discountRate;
	}

	public static CreateItemDiscountRequest of(Long itemId, DiscountType discountType, int discountRate) {
		return new CreateItemDiscountRequest(itemId, discountType, discountRate);
	}

	public Long getItemId() {
		return itemId;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public int getDiscountRate() {
		return discountRate;
	}
}
