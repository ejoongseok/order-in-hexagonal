package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.service.CancelOrderValidator;
import kata.orderinhexagonal.order.domain.Order;

@SpringBootTest
class CancelOrderValidatorTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;
	@Autowired
	CancelOrderValidator cancelOrderValidator;

	@Test
	@DisplayName("주문자와 요청자가 동일 한지 확인")
	void isOrdererAndTheRequestorMatchTest() {
		// given
		Member orderer = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(orderer.getId());
		// when
		boolean isMatches = cancelOrderValidator.isOrdererAndTheRequestorMatch(orderer, order.getMember());
		// then
		Assertions.assertThat(isMatches).isTrue();
	}

}
