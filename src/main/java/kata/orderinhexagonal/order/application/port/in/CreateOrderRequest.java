package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderRequest {
	List<OrderItemRequest> orderItemRequests = new ArrayList<>();

	public CreateOrderRequest(List<OrderItemRequest> orderItemRequests) {
		this.orderItemRequests = orderItemRequests;
	}

	public static CreateOrderRequest of(List<OrderItemRequest> orderItemRequests) {
		return new CreateOrderRequest(orderItemRequests);
	}
}
