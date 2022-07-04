package kata.orderinhexagonal.discount.application.port.in;

import kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemDiscountResponse {
	private Long id;
	private DiscountType discountType;
	private int discountRate;
}
