package kata.orderinhexagonal.order;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.discount.domain.DiscountType;
import kata.orderinhexagonal.fixture.DiscountFixture;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.Order;

@SpringBootTest
class DiscountItemOrderTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;
	@Autowired
	ItemFixture itemFixture;
	@Autowired
	DiscountFixture discountFixture;

	@Autowired
	StockFixture stockFixture;

	@Autowired
	CreateOrderUsecase createOrderUsecase;

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
	void 할인_적용_상품_주문() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Item item = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item, 10);
		CreateOrderRequest orderRequest = CreateOrderRequest.of(List.of(OrderItemRequest.of(item.getId(), 2)));
		orderRequest.assignOrdererId(member.getId());

		Item item2 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item2, 10);
		CreateOrderRequest orderRequest2 = CreateOrderRequest.of(List.of(OrderItemRequest.of(item2.getId(), 2)));
		orderRequest2.assignOrdererId(member.getId());
		// when
		discountFixture.createItemDiscount(item.getId(), DiscountType.FIXED, 10000);
		discountFixture.createItemDiscount(item2.getId(), DiscountType.RATE, 10);
		Order order = createOrderUsecase.createOrder(orderRequest);
		Order order2 = createOrderUsecase.createOrder(orderRequest2);

		// then
		Assertions.assertThat(order.getTotalPrice()).isEqualTo(1_990_000);
		Assertions.assertThat(order2.getTotalPrice()).isEqualTo(1_800_000);
	}

}
