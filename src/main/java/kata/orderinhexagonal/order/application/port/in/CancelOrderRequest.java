package kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelOrderRequest {
	private Long orderId;

	private Long ordererId;

	public CancelOrderRequest(Long orderId) {
		this.orderId = orderId;
	}

	public static CancelOrderRequest of(Long orderId) {
		return new CancelOrderRequest(orderId);
	}


	public void assignOrdererId(long ordererId) {
		this.ordererId = ordererId;
	}
}
