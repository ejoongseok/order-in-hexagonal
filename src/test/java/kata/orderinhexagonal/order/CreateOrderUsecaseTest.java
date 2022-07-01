package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.order.domain.OrderStatus;

class CreateOrderUsecaseTest {

	@Test
	void 상품_주문() {
		// given

		// when

		// then
		Assertions.assertThat(order.getId()).isPositive();
		Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(order.getTotalPrice()).hasSize(totalPrice);
		Assertions.assertThat(order.getOrderItems()).hasSize(2);
		Assertions.assertThat(order.getOrderItems().get(0).getId()).isPositive();
		Assertions.assertThat(order.getOrderItems().get(0).getItemId()).isEqualTo(orderItem1.getId());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(order.getOrderItems().get(0).getPrice()).isEqualTo(orderItem1Price);
		Assertions.assertThat(order.getOrderItems().get(0).getOrderQuantity()).isEqualTo(orderItem1Quantity);
		Assertions.assertThat(order.getOrderItems().get(1).getId()).isPositive();
		Assertions.assertThat(order.getOrderItems().get(1).getItemId()).isEqualTo(orderItem2.getId());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(order.getOrderItems().get(1).getPrice()).isEqualTo(orderItem2Price);
		Assertions.assertThat(order.getOrderItems().get(1).getOrderQuantity()).isEqualTo(orderItem2Quantity);
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(orderItem1.getStockQuantity() - orderItem1Quantity);
		Assertions.assertThat(refreshItem2.getStockQuantity()).isEqualTo(orderItem2.getStockQuantity() - orderItem2Quantity);
	}

}
