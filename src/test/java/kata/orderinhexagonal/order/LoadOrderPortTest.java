package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;

@SpringBootTest
class LoadOrderPortTest {

	@Autowired
	OrderFixture orderFixture;
	@Autowired
	MemberFixture memberFixture;
	@Autowired
	LoadOrderPort loadOrderPort;

	@Test
	void 주문정보_불러오기() {
		// given
		Member orderer = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order createOrder = orderFixture.createOrder(orderer.getId());
		// when
		Order loadOrder = loadOrderPort.loadOrder(createOrder.getId());
		// then
		Assertions.assertThat(loadOrder.getId()).isEqualTo(createOrder.getId());
		Assertions.assertThat(loadOrder.getTotalPrice()).isEqualTo(createOrder.getTotalPrice());
		Assertions.assertThat(loadOrder.getStatus()).isEqualTo(createOrder.getStatus());
		Assertions.assertThat(loadOrder.getOrderItems()).hasSize(createOrder.getOrderItems().size());
		OrderItem loadOrderItem = loadOrder.getOrderItems().get(0);
		OrderItem createOrderItem = createOrder.getOrderItems().get(0);
		Assertions.assertThat(loadOrderItem.getOrderPrice()).isEqualTo(createOrderItem.getOrderPrice());
		Assertions.assertThat(loadOrderItem.getOrderQuantity()).isEqualTo(createOrderItem.getOrderQuantity());
		Item loadItem = loadOrderItem.getItem();
		Item createItem = createOrderItem.getItem();
		Assertions.assertThat(loadItem.getName()).isEqualTo(createItem.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqualTo(createItem.getPrice());
		Assertions.assertThat(loadItem.getStockQuantity()).isEqualTo(createItem.getStockQuantity());
	}

}
