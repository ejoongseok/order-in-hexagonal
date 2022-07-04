package kata.orderinhexagonal.order.domain;

import kata.orderinhexagonal.item.domain.Item;

public class OrderItem {
	private Long id;
	private Item item;
	private Order order;
	private int orderPrice;
	private int orderQuantity;

	public OrderItem(Order order, Item item, int orderQuantity, int orderPrice) {
		this.order = order;
		this.item = item;
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
	}

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

	public void assignId(Long id) {
		this.id = id;
	}
}
