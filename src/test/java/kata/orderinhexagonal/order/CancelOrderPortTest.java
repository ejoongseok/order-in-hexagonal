package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.out.CancelOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
class CancelOrderPortTest {

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	CancelOrderPort cancelOrderPort;

	@Test
	void cancelOrderTest() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order cancelOrder = orderFixture.createOrder(member.getId());
		// when
	    cancelOrderPort.cancel(cancelOrder);
		// then
		Assertions.assertThat(cancelOrder.getStatus()).isEqualTo(OrderStatus.CANCELED);
	}

}
