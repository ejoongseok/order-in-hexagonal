package kata.orderinhexagonal.delivery.domain;

import java.time.LocalDateTime;

import kata.orderinhexagonal.order.domain.Order;

public class Delivery {
	private Long id;
	private Order order;
	private DeliveryStatus status;
	private String location;
	private LocalDateTime createdDateTime;

	public Delivery(Order order, DeliveryStatus deliveryStatus, String location) {
		this.order = order;
		this.status = deliveryStatus;
		this.location = location;
		this.createdDateTime = LocalDateTime.now();
	}

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

	public void assignId(Long id) {
		this.id = id;
	}
}
