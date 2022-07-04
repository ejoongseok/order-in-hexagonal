package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.DiscountType;

public class CreateItemDiscountResponse {
	private Long id;
	private DiscountType discountType;
	private int discountRate;

	public Long getId() {
		return id;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public int getDiscountRate() {
		return discountRate;
	}
}
