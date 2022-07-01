package kata.orderinhexagonal.order;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
class CreateOrderUsecaseTest {

	CreateOrderUsecase createOrderUsecase = new OrderService();
	@Autowired ItemFixture itemFixture;

	@Autowired StockFixture stockFixture;

	@Autowired MemberFixture memberFixture;

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
		CreateOrderRequest request = CreateOrderRequest.of(List.of(orderItemRequest1, orderItemRequest2));
		request.assignOrdererId(member.getId());

		// when
		Order order = createOrderUsecase.createOrder(request);

		// then
		Assertions.assertThat(order.getId()).isPositive();
		Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(order.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(order.getOrderItems()).hasSize(2);
		Assertions.assertThat(order.getMember().getId()).isEqualTo(member.getId());
		주문상품_검증(order, 0, orderItem1, orderItem1Price, orderItem1Quantity);
		주문상품_검증(order, 1, orderItem2, orderItem2Price, orderItem2Quantity);
		상품_남은수량_검증(orderItem1, orderItem1Quantity, itemFixture.getItem(orderItem1.getId()));
		상품_남은수량_검증(orderItem2, orderItem2Quantity, itemFixture.getItem(orderItem2.getId()));
	}

	void 상품_남은수량_검증(Item orderItem, int orderItemQuantity, Item currentItem) {
		Assertions.assertThat(currentItem.getStockQuantity()).isEqualTo(
			orderItem.getStockQuantity() - orderItemQuantity);
	}

	void 주문상품_검증(Order order, int index, Item orderItem, int orderItemPrice, int orderItemQuantity) {
		Assertions.assertThat(order.getOrderItems().get(index).getId()).isPositive();
		Assertions.assertThat(order.getOrderItems().get(index).getItem().getId()).isEqualTo(orderItem.getId());
		Assertions.assertThat(order.getOrderItems().get(index).getOrder().getId()).isEqualTo(order.getId());
		Assertions.assertThat(order.getOrderItems().get(index).getOrderPrice()).isEqualTo(orderItemPrice);
		Assertions.assertThat(order.getOrderItems().get(index).getOrderQuantity()).isEqualTo(orderItemQuantity);
	}

	private static class OrderService implements CreateOrderUsecase {
		@Override
		public Order createOrder(CreateOrderRequest request) {

			return null;
		}
	}
}
