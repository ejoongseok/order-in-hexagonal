package kata.orderinhexagonal.delivery.domain;

import java.time.LocalDateTime;

import kata.orderinhexagonal.order.domain.Order;

public class Delivery {
	private Long id;
	private Order order;
	private DeliveryStatus status;
	private String location;
	private LocalDateTime createdDateTime;

	public Long getId() {
		return id;
	}

	public Order getOrder() {
		return order;
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
}
