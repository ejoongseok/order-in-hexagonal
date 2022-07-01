package kata.orderinhexagonal.order;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.OrderStatus;

class CreateOrderUsecaseTest {

	CreateOrderUsecase createOrderUsecase;
	ItemFixture itemFixture;

	StockFixture stockFixture;

	MemberFixture memberFixture;

	@Test
	void 상품_주문() {
		// given
		Item orderItem1 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(orderItem1, 10);
		Item orderItem2 = itemFixture.createItem("가방", 150_000);
		stockFixture.stockIn(orderItem2, 10);

		String name = "이중석";
		String email = "ejoongseok@gmail.com";
		String location = "대전광역시 서구";
		Member member = memberFixture.createMember(name, email, location);

		int orderItem1Quantity = 1;
		int orderItem2Quantity = 3;
		int orderItem1Price = orderItem1Quantity * orderItem1.getPrice();
		int orderItem2Price = orderItem2Quantity * orderItem2.getPrice();
		int totalPrice = orderItem1Price + orderItem2Price;

		OrderItemRequest orderItemRequest1 = OrderItemRequest.of(orderItem1.getId(), orderItem1Quantity);
		OrderItemRequest orderItemRequest2 = OrderItemRequest.of(orderItem2.getId(), orderItem2Quantity);
		CreateOrderRequest request = CreateOrderRequest.of(member, List.of(orderItemRequest1, orderItemRequest2));

		// when
		Order order = createOrderUsecase.createOrder(request);

		// then
		Assertions.assertThat(order.getId()).isPositive();
		Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(order.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(order.getOrderItems()).hasSize(2);
		Assertions.assertThat(order.getOrderItems().get(0).getId()).isPositive();
		Assertions.assertThat(order.getOrderItems().get(0).getItemId()).isEqualTo(orderItem1.getId());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderPrice()).isEqualTo(orderItem1Price);
		Assertions.assertThat(order.getOrderItems().get(0).getOrderQuantity()).isEqualTo(orderItem1Quantity);
		Assertions.assertThat(order.getOrderItems().get(1).getId()).isPositive();
		Assertions.assertThat(order.getOrderItems().get(1).getItemId()).isEqualTo(orderItem2.getId());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderPrice()).isEqualTo(orderItem2Price);
		Assertions.assertThat(order.getOrderItems().get(1).getOrderQuantity()).isEqualTo(orderItem2Quantity);
		Item refreshItem1 = itemFixture.getItem(orderItem1.getId());
		Item refreshItem2 = itemFixture.getItem(orderItem2.getId());
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(orderItem1.getStockQuantity() - orderItem1Quantity);
		Assertions.assertThat(refreshItem2.getStockQuantity()).isEqualTo(orderItem2.getStockQuantity() - orderItem2Quantity);
	}

	private static class Order {
		private Long id;
		private OrderStatus status;
		private List<OrderItem> orderItems = new ArrayList<>();

		public int getTotalPrice() {
			return orderItems.stream().mapToInt(OrderItem::getOrderPrice).sum();
		}

		public Long getId() {
			return id;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public List<OrderItem> getOrderItems() {
			return orderItems;
		}
	}

	private static class OrderItem {
		private Long id;
		private Long itemId;
		private Long orderId;
		private int orderPrice;
		private int orderQuantity;

		public Long getId() {
			return id;
		}

		public Long getItemId() {
			return itemId;
		}

		public Long getOrderId() {
			return orderId;
		}

		public int getOrderPrice() {
			return orderPrice;
		}

		public int getOrderQuantity() {
			return orderQuantity;
		}
	}
}
