package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentStatus;
import kata.orderinhexagonal.payment.domain.PaymentType;

@SpringBootTest
class RequestPayPortTest {

	@Autowired
	MemberFixture memberFixture;
	@Autowired
	OrderFixture orderFixture;
	@Autowired
	RequestPayPort requestPayPort;

	@Test
	void requestPayTest() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());

		CardType cardType = CardType.CREDIT_CARD;
		CardCompany cardCompany = CardCompany.KATA;
		String cardNumber = "1234567890123456";
		String cardCvc = "123";
		int paymentPrice = order.getTotalPrice();
		PaymentType paymentType = PaymentType.PAY_IN_FULL;

		// when
		PaymentStatus resultPaymentStatus = requestPayPort.pay(cardType, cardCompany, cardNumber, cardCvc, paymentPrice, paymentType);

		// then
		Assertions.assertThat(resultPaymentStatus).isEqualTo(PaymentStatus.OK);
	}
	
}
