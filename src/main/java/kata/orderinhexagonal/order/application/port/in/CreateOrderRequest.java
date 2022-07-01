package kata.orderinhexagonal.order.application.port.in;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
	@NotNull(message = "주문할 회원의 ID가 없습니다.")
	private Member orderMember;

	public CreateOrderRequest(Member orderMember, List<OrderItemRequest> orderItemRequest) {
		this.orderMember = orderMember;
		this.orderItemRequests = orderItemRequest;
	}

	public static CreateOrderRequest of(Member member, List<OrderItemRequest> orderItemRequest) {
		return new CreateOrderRequest(member, orderItemRequest);
	}
}