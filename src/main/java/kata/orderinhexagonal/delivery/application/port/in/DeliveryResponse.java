package kata.orderinhexagonal.delivery.application.port.in;

import java.time.LocalDateTime;

import kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.Getter;

@Getter
public class DeliveryResponse {
	private Long id;
	private DeliveryStatus status;
	private String location;
	private LocalDateTime createdDateTime;
	private Long orderId;
}
