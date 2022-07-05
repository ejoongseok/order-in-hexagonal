package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.domain.OrderItem;

class LoadOrderPortTest {

	@Test
	void 주문정보_불러오기() {
		// given

		// when

		// then
		Assertions.assertThat(loadOrder.getId()).isEqaulTo(createOrder.getId());
		Assertions.assertThat(loadOrder.getTotalPrice()).isEqaulTo(careteOrder.getTotalPrice());
		Assertions.assertThat(loadOrder.getStatus()).isEqaulTo(careteOrder.getStatus());
		Assertions.assertThat(loadOrder.getOrderItems).hasSize(careteOrder.getOrderItems.size());
		OrderItem loadOrderItem = loadOrder.getOrderItems.get(0);
		OrderItem createOrderItem = careteOrder.getOrderItems.get(0);
		Assertions.assertThat(loadOrderItem.getOrderPrice()).isEqaulTo(createOrderItem.getOrderPrice());
		Assertions.assertThat(loadOrderItem.getOrderQuantity()).isEqaulTo(createOrderItem.getOrderQuantity());
		Item loadItem = loadOrderItem.getItem();
		Item createItem = createOrderItem.getItem();
		Assertions.assertThat(loadItem.getName()).isEqaulTo(createItem.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqaulTo(createItem.getPrice());
		Assertions.assertThat(loadItem.getStockQuantity()).isEqaulTo(createItem.getStockQuantity());
	}

}
