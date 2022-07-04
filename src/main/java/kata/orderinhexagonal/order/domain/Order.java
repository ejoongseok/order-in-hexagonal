package kata.orderinhexagonal.order.domain;

import java.util.ArrayList;
import java.util.List;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;

public class Order {
	private Long id;
	private OrderStatus status;
	private List<OrderItem> orderItems = new ArrayList<>();
	private Member member;

	public Order(Member orderer) {
		this.member = orderer;
		this.status = OrderStatus.NOT_PAYED;
	}

	public int getTotalPrice() {
		return orderItems.stream().mapToInt(OrderItem::getOrderPrice).sum();
	}

	public Long getId() {
		return id;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public Member getMember() {
		return member;
	}

	public void addOrderItem(Item item, int orderQuantity, int orderPrice) {
		orderItems.add(new OrderItem(this,item, orderQuantity, orderPrice));
	}

	public void assignId(Long id) {
		this.id = id;
	}
}
