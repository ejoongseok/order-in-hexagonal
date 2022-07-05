package kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelOrderRequest {
	private Long orderId;

	public CancelOrderRequest(Long orderId) {
		this.orderId = orderId;
	}

	public static CancelOrderRequest of(Long orderId) {
		return new CancelOrderRequest(orderId);
	}

	public Long getOrderId() {
		return orderId;
	}
}
