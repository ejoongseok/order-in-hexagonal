package kata.orderinhexagonal.delivery.application.port.in;

import javax.validation.constraints.NotNull;

import kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.Getter;

@Getter
public class DeliveryRequest {
	@NotNull(message = "주문 번호를 입력해주세요.")
	private Long orderId;
	@NotNull(message = "배송 상태를 입력해주세요.")
	private DeliveryStatus deliveryStatus;
	@NotNull(message = "배송 위치를 입력해주세요.")
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
