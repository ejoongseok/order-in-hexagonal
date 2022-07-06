package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.order.domain.OrderStatus;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

class PaymentUsecaseTest {

	@Test
	void 주문결제() {
		// given

		// when

		// then
		Assertions.assertThat(payment.getId()).isPositive();
		Assertions.assertThat(payment.getOrderId()).isEqualTo(order.getId());
		Assertions.assertThat(payment.getPaymentPrice()).isEqualTo(order.getTotalPrice());
		Assertions.assertThat(payment.getCardType()).isEqualTo(CardType.CREDIT_CARD);
		Assertions.assertThat(payment.getCardCompany()).isEqualTo(CardCompany.KATA);
		Assertions.assertThat(payment.getPaymentType()).isEqualTo(PaymentType.PAY_IN_FULL);
		Assertions.assertThat(payment.getCardNumber()).isEqualTo(cardNumber);
		Assertions.assertThat(payment.getCardCvc()).isEqualTo(cardCvc);
		Assertions.assertThat(order.getStatus()).isEqualTo(OrderStatus.PAYED);
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.PAYED);
	}

}
