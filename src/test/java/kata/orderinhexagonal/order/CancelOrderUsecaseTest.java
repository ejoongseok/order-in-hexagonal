package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.in.web.OrderController;
import kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CancelOrderUsecase;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
class CancelOrderUsecaseTest {

	@Autowired
	OrderFixture orderFixture;
	@Autowired
	MemberFixture memberFixture;

	@Autowired
	CancelOrderUsecase cancelOrderUsecase;

	@Test
	void 주문_취소() {
		// given
		Member orderer = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order createOrder = orderFixture.createOrder(orderer.getId());
		CancelOrderRequest request = CancelOrderRequest.of(createOrder.getId());
		// when
		Order cancelOrder = cancelOrderUsecase.cancelOrder(request);
		// then
		Assertions.assertThat(cancelOrder.getId()).isEqualTo(createOrder.getId());
		Assertions.assertThat(cancelOrder.getStatus()).isEqualTo(OrderStatus.CANCELED);
	}

}
