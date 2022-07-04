package kata.orderinhexagonal.order;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;

class SaveOrderPortTest {

	LoadOrdererPort loadOrdererPort;
	LoadOrderItemPort loadOrderItemPort;
	ItemOrderStockOutPort itemOrderStockOutPort;

	ItemFixture itemFixture;

	StockFixture stockFixture;

	@Test
	void saveOrderTest() {
		// given
		Item item1 = itemFixture.createItem("가방", 100_000);
		Item item2 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item1, 10);
		stockFixture.stockIn(item2, 20);

		CreateOrderRequest request = CreateOrderRequest.of(List.of(
				OrderItemRequest.of(item1.getId(), 5),
				OrderItemRequest.of(item2.getId(), 10)
			));


		Member orderer = loadOrdererPort.load(request.getOrdererId());
		Order order = new Order(orderer);
		request.getOrderItemRequests().forEach(orderItemRequest -> {
			Item item = loadOrderItemPort.loadItem(orderItemRequest.getItemId());
			int orderPrice = item.getPrice() * orderItemRequest.getOrderQuantity();
			order.addOrderItem(item, orderItemRequest.getOrderQuantity(), orderPrice);
		});
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			itemOrderStockOutPort.stockOut(orderItem.getItem(), orderItem.getOrderQuantity());
		}

		// when
		saveOrderPort.save(order);
		// then
		Assertions.assertThat(order.getId()).isPositive();
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(order.getId()).isEqualTo(orderEntity.getId());
		Assertions.assertThat(order.getMember().getName()).isEqualTo(orderEntity.getMemberEntity().getName());
		Assertions.assertThat(order.getStatus()).isEqualTo(orderEntity.getStatus());
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
