package kata.orderinhexagonal.order.domain;

import java.util.ArrayList;
import java.util.List;

import kata.orderinhexagonal.member.domain.Member;

public class Order {
	private Long id;
	private OrderStatus status;
	private List<OrderItem> orderItems = new ArrayList<>();
	private Member member;

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
}
