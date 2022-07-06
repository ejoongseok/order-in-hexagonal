package kata.orderinhexagonal.payment;

import java.util.concurrent.Flow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.fixture.PaymentFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import kata.orderinhexagonal.payment.domain.Payment;
import kata.orderinhexagonal.payment.domain.PaymentStatus;

@SpringBootTest
class CancelPaymentPortTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	PaymentFixture paymentFixture;
	@Autowired
	CancelPaymentPort cancelPaymentPort;

	@BeforeEach
	void setUp() {
		paymentFixture.clearPayment();
		memberFixture.clearMember();
		orderFixture.clearOrder();
	}

	@AfterEach
	void tearDown() {
		paymentFixture.clearPayment();
		memberFixture.clearMember();
		orderFixture.clearOrder();
	}



	@Test
	void 결제_취소() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		Payment payment = paymentFixture.createPayment(order.getId());
		// when
		cancelPaymentPort.request(order);
		// then
		PaymentEntity paymentEntity = paymentFixture.getPaymentEntity(payment.getId());
		Assertions.assertThat(paymentEntity.getStatus()).isEqualTo(PaymentStatus.CANCELLATION_REQUEST);
	}


}
