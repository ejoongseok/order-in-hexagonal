package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.application.port.out.OrderStatusChangePort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
class OrderStatusChangePortTest {

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	MemberFixture memberFixture;
	@Autowired
	OrderStatusChangePort orderStatusChangePort;

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
	void 주문상태변경() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		order.updateDeliveredStatus();
		// when
	    orderStatusChangePort.statusChange(order);
		// then
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.DELIVERED);
	}

}
