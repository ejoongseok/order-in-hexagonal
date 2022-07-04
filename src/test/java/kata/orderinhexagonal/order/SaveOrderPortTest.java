package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SaveOrderPortTest {

	@Test
	void saveOrderTest() {
		// given

		// when

		// then
		Assertions.assertThat(order.getId()).isPositive();
		Assertions.assertThat(order.getId()).isEqualTo(orderEntity.getId());
		Assertions.assertThat(order.getMember().getName()).isEqualTo(orderEntity.getMemberEntity().getName());
		Assertions.assertThat(order.getMember().getStatus()).isEqualTo(orderEntity.getStatus());
		Assertions.assertThat(order.getOrderItems().size()).isEqualTo(orderEntity.getOrderItemsEntity().size());
		Assertions.assertThat(order.getOrderItems().get(0).getId()).isEqualTo(orderEntity.getOrderItemsEntity().get(0).getId());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderPrice()).isEqualTo(orderEntity.getOrderItemsEntity().get(0).getOrderPrice());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderQuantity()).isEqualTo(orderEntity.getOrderItemsEntity().get(0).getOrderQuantity());
		Assertions.assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(orderEntity.getOrderItemsEntity().get(0).getItemEntity().getStockQuantity());
		Assertions.assertThat(order.getOrderItems().get(0).getItem().getName()).isEqualTo(orderEntity.getOrderItemsEntity().get(0).getItemEntity().getName());
		Assertions.assertThat(order.getOrderItems().get(1).getId()).isEqualTo(orderEntity.getOrderItemsEntity().get(1).getId());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderPrice()).isEqualTo(orderEntity.getOrderItemsEntity().get(1).getOrderPrice());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderQuantity()).isEqualTo(orderEntity.getOrderItemsEntity().get(1).getOrderQuantity());
		Assertions.assertThat(order.getOrderItems().get(1).getItem().getStockQuantity()).isEqualTo(orderEntity.getOrderItemsEntity().get(1).getItemEntity().getStockQuantity());
		Assertions.assertThat(order.getOrderItems().get(1).getItem().getName()).isEqualTo(orderEntity.getOrderItemsEntity().get(1).getItemEntity().getName());
	}


}
