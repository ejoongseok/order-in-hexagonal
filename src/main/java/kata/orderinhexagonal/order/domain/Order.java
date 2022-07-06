package kata.orderinhexagonal.order.domain;

import java.util.ArrayList;
import java.util.List;

import kata.orderinhexagonal.discount.domain.Discount;
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

	public Order(Long id, Member member, OrderStatus status) {
		this.id = id;
		this.member = member;
		this.status = status;
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
	public int calculatePrice(int orderItemQuantity, int price, Discount discount) {
		int orderPrice = price * orderItemQuantity;
		if(discount != null) {
			return discount.discountPrice(orderPrice);
		}
		return orderPrice;
	}

	public boolean isOrdererAndTheRequesterMatch(Member cancelRequestor) {
		return this.getMember().equals(cancelRequestor);
	}

	public boolean isDeliverd() {
		return OrderStatus.DELIVERED.equals(this.status);
	}

	public boolean isPayed() {
		return OrderStatus.PAYED.equals(this.status);
	}

	public void cancel() {
		this.status = OrderStatus.CANCELED;
	}

	public void payed() {
		this.status = OrderStatus.PAYED;
	}
}
