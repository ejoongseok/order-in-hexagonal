package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.payment.application.port.out.OrderLoadPort;

@SpringBootTest
class OrderLoadPortTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	OrderLoadPort orderLoadPort;

	@Test
	void 주문정보불러오기() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		// when
		Order loadOrder = orderLoadPort.load(order.getId());

		// then
		Assertions.assertThat(loadOrder).isNotNull();
		Assertions.assertThat(loadOrder.getMember()).isNotNull();
		Assertions.assertThat(loadOrder.getOrderItems()).isNotEmpty();
		Assertions.assertThat(loadOrder.getOrderItems()).hasSize(1);
	}

}
