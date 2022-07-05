package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemDiscountResponse {
	private Long id;
	private Long itemId;
	private DiscountType discountType;
	private int discountRate;

	public CreateItemDiscountResponse(Long id, Long itemId, DiscountType discountType, int discountValue) {
		this.id = id;
		this.itemId = itemId;
		this.discountType = discountType;
		this.discountRate = discountValue;
	}
}
