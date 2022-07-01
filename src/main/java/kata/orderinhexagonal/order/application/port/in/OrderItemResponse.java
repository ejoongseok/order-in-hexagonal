package kata.orderinhexagonal.order.application.port.in;

import lombok.Getter;

@Getter
public class OrderItemResponse {
	private long itemId;
	private int quantity;
	private int price;
}
