package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;
import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

@SpringBootTest
class PaymentUsecaseTest {

	@Autowired
	OrderFixture orderFixture;
	@Autowired
	MemberFixture memberFixture;
	@Autowired
	PaymentUsecase paymentUsecase;

	@Test
	void 주문결제() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());

		String cardNumber = "1234567890123456";
		String cardCvc = "123";
		PaymentRequest request = PaymentRequest.of(order.getId(), order.getTotalPrice(), CardType.CREDIT_CARD,
			CardCompany.KATA,
			cardNumber, cardCvc, PaymentType.PAY_IN_FULL);

		// when
		Payment payment = paymentUsecase.payments(request);

		// then
		Assertions.assertThat(payment.getId()).isPositive();
		Assertions.assertThat(payment.getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(payment.getPaymentPrice()).isEqualTo(order.getTotalPrice());
		Assertions.assertThat(payment.getCardType()).isEqualTo(CardType.CREDIT_CARD);
		Assertions.assertThat(payment.getCardCompany()).isEqualTo(CardCompany.KATA);
		Assertions.assertThat(payment.getPaymentType()).isEqualTo(PaymentType.PAY_IN_FULL);
		Assertions.assertThat(payment.getCardNumber()).isEqualTo(cardNumber);
		Assertions.assertThat(payment.getCardCvc()).isEqualTo(cardCvc);
		Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.OK);
		Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.PAYED);
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.PAYED);
	}

	public enum PaymentStatus {
		OK, FAILED, CANCELED
	}

	private static class Payment {
		private Long id;
		private Long orderId;
		private int paymentPrice;
		private PaymentType paymentType;
		private CardType cardType;
		private CardCompany cardCompany;
		private String cardNumber;
		private String cardCvc;
		private PaymentStatus status;

		public Long getId() {
			return id;
		}

		public Long getOrderId() {
			return orderId;
		}

		public int getPaymentPrice() {
			return paymentPrice;
		}

		public PaymentType getPaymentType() {
			return paymentType;
		}

		public CardType getCardType() {
			return cardType;
		}

		public CardCompany getCardCompany() {
			return cardCompany;
		}

		public String getCardNumber() {
			return cardNumber;
		}

		public String getCardCvc() {
			return cardCvc;
		}

		public PaymentStatus getStatus() {
			return status;
		}
	}
}
