package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.OrderStatus;

public class CreateOrderResponse {
	private Long id;
	private List<OrderItemResponse> orderItems = new ArrayList<>();
	private int totalPrice;
	private OrderStatus status;
	private Member member;

	public Long getId() {
		return id;
	}

	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public Member getMember() {
		return member;
	}
}
