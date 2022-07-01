package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class OrderApiTest {

	@Test
	void 상품_주문() {
		// given

		// when

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(CreateOrderResponse.getId()).isPositive();
		Assertions.assertThat(CreateOrderResponse.getOrderItems()).isSize(2);
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(0).getQuantity()).isEqualTo(orderItem1.getQuantity());
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(0).getPrice()).isEqualTo(orderItem1TotalPrice);
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(1).getQuantity()).isEqualTo(orderItem2.getQuantity());
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(1).getPrice()).isEqualTo(orderItem2TotalPrice);
		Assertions.assertThat(CreateOrderResponse.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(CreateOrderResponse.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(CreateOrderResponse.getMember().getName()).isEqualTo(member.getName());
	}

}
