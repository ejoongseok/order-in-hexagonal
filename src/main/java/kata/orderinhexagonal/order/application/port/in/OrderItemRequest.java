package kata.orderinhexagonal.order.application.port.in;

public class OrderItemRequest {
	private Long id;
	private int orderQuantity;

	public OrderItemRequest(Long id, int orderQuantity) {
		this.id = id;
		this.orderQuantity = orderQuantity;
	}

	public static OrderItemRequest of(Long id, int orderQuantity) {
		return new OrderItemRequest(id, orderQuantity);
	}
}
