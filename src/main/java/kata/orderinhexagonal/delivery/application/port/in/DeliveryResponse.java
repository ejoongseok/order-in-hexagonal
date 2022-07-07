package kata.orderinhexagonal.delivery.application.port.in;

import java.time.LocalDateTime;

public class DeliveryResponse {
	private Long id;
	private DeliveryStatus status;
	private String location;
	private LocalDateTime createdDateTime;
	private Long orderId;

	public Long getId() {
		return id;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public String getLocation() {
		return location;
	}

	public LocalDateTime getCreatedDateTime() {
		return createdDateTime;
	}

	public Long getOrderId() {
		return orderId;
	}
}
