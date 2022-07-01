package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class CreateOrderRequest {

	@NotEmpty(message = "주문할 상품을 추가해 주세요.")
	@Size(min = 1, message = "주문할 상품을 추가해 주세요.")
	List<OrderItemRequest> orderItemRequests = new ArrayList<>();

	public CreateOrderRequest(List<OrderItemRequest> orderItemRequests) {
		this.orderItemRequests = orderItemRequests;
	}

	public static CreateOrderRequest of(List<OrderItemRequest> orderItemRequests) {
		return new CreateOrderRequest(orderItemRequests);
	}
}
