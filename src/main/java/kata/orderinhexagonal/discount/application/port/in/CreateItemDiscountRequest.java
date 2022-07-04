package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
