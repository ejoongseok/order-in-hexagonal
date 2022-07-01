package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import kata.orderinhexagonal.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderRequest {

	@NotEmpty(message = "주문할 상품을 추가해 주세요.")
	@Size(min = 1, message = "주문할 상품을 추가해 주세요.")
	private List<OrderItemRequest> orderItemRequests = new ArrayList<>();

	private Long ordererId;

	public CreateOrderRequest(List<OrderItemRequest> orderItemRequest) {
		this.orderItemRequests = orderItemRequest;
	}

	public static CreateOrderRequest of(List<OrderItemRequest> orderItemRequest) {
		return new CreateOrderRequest(orderItemRequest);
	}

	public void assignOrdererId(Long ordererId) {
		this.ordererId = ordererId;
	}
}
