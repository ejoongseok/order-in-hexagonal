package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.delivery.application.port.out.OrderDeliveryLoadPort;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;

@SpringBootTest
class OrderDeliveryLoadPortTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	OrderDeliveryLoadPort orderDeliveryLoadPort;

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
	void 주문_불러오기() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());

		// when
		Order loadOrder =  orderDeliveryLoadPort.load(order.getId());
		// then
		Assertions.assertThat(loadOrder).isNotNull();
		Assertions.assertThat(loadOrder.getId()).isEqualTo(order.getId());
	}

}
