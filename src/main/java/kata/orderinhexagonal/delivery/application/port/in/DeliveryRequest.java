package kata.orderinhexagonal.delivery.application.port.in;

import kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.Getter;

@Getter
public class DeliveryRequest {
	private Long orderId;
	private DeliveryStatus deliveryStatus;
	private String location;

	public DeliveryRequest(Long orderId, DeliveryStatus deliveryStatus, String location) {
		this.orderId = orderId;
		this.deliveryStatus = deliveryStatus;
		this.location = location;
	}

	public static DeliveryRequest of(Long orderId, DeliveryStatus deliveryStatus, String location) {
		return new DeliveryRequest(orderId, deliveryStatus, location);
	}
}
