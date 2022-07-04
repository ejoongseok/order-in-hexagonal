package kata.orderinhexagonal.order;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.fixture.OrderItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderItemEntity;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import kata.orderinhexagonal.order.application.port.out.SaveOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;

@SpringBootTest
class SaveOrderPortTest {

	@Autowired LoadOrdererPort loadOrdererPort;
	@Autowired LoadOrderItemPort loadOrderItemPort;
	@Autowired ItemOrderStockOutPort itemOrderStockOutPort;

	@Autowired ItemFixture itemFixture;

	@Autowired StockFixture stockFixture;

	@Autowired
	MemberFixture memberFixture;
	@Autowired
	SaveOrderPort saveOrderPort;
	@Autowired
	OrderFixture orderFixture;
	@Autowired
	OrderItemFixture orderItemFixture;

	@BeforeEach
	void setUp() {
		orderFixture.clearOrder();
	    memberFixture.clearMember();
	}

	@AfterEach
	void tearDown() {
		orderFixture.clearOrder();
		memberFixture.clearMember();
	}



	@Test
	void saveOrderTest() throws InterruptedException {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Item item1 = itemFixture.createItem("가방", 100_000);
		Item item2 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item1, 10);
		stockFixture.stockIn(item2, 20);

		CreateOrderRequest request = CreateOrderRequest.of(List.of(
				OrderItemRequest.of(item1.getId(), 5),
				OrderItemRequest.of(item2.getId(), 10)
			));
		request.assignOrdererId(member.getId());

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

		Thread.sleep(1000);

		// when
		saveOrderPort.save(order);
		// then
		Assertions.assertThat(order.getId()).isPositive();
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(order.getId()).isEqualTo(orderEntity.getId());
		MemberEntity memberEntity = memberFixture.getMember(member.getId());
		Assertions.assertThat(order.getMember().getName()).isEqualTo(memberEntity.getName());
		Assertions.assertThat(order.getStatus()).isEqualTo(orderEntity.getStatus());
		List<OrderItemEntity> orderItemEntities = orderItemFixture.getOrderItems(order.getId());
		Assertions.assertThat(order.getOrderItems()).hasSize(orderItemEntities.size());
		Assertions.assertThat(order.getOrderItems().get(0).getId()).isEqualTo(orderItemEntities.get(0).getId());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderPrice()).isEqualTo(orderItemEntities.get(0).getOrderPrice());
		Assertions.assertThat(order.getOrderItems().get(0).getOrderQuantity()).isEqualTo(orderItemEntities.get(0).getOrderQuantity());
		Assertions.assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(orderItemEntities.get(0).getItem().getStockQuantity());
		Assertions.assertThat(order.getOrderItems().get(0).getItem().getName()).isEqualTo(orderItemEntities.get(0).getItem().getName());
		Assertions.assertThat(order.getOrderItems().get(1).getId()).isEqualTo(orderItemEntities.get(1).getId());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderPrice()).isEqualTo(orderItemEntities.get(1).getOrderPrice());
		Assertions.assertThat(order.getOrderItems().get(1).getOrderQuantity()).isEqualTo(orderItemEntities.get(1).getOrderQuantity());
		Assertions.assertThat(order.getOrderItems().get(1).getItem().getStockQuantity()).isEqualTo(orderItemEntities.get(1).getItem().getStockQuantity());
		Assertions.assertThat(order.getOrderItems().get(1).getItem().getName()).isEqualTo(orderItemEntities.get(1).getItem().getName());
		Assertions.assertThat(order.getTotalPrice()).isEqualTo(orderItemEntities.stream().mapToInt(OrderItemEntity::getOrderPrice).sum());
	}


}
