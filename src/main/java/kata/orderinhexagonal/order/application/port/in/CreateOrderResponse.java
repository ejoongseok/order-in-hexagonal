package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

import kata.orderinhexagonal.order.domain.OrderStatus;
import lombok.Getter;

@Getter
public class CreateOrderResponse {
	private Long id;
	private List<OrderItemResponse> orderItems = new ArrayList<>();
	private int totalPrice;
	private OrderStatus status;
}
