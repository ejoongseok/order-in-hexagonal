package kata.orderinhexagonal.order.domain;

import kata.orderinhexagonal.item.domain.Item;

public class OrderItem {
	private Long id;
	private Item item;
	private Order order;
	private int orderPrice;
	private int orderQuantity;

	public Long getId() {
		return id;
	}

	public Item getItem() {
		return item;
	}

	public Order getOrder() {
		return order;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}
}
