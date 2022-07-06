package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.order.domain.OrderStatus;
import kata.orderinhexagonal.payment.domain.PaymentStatus;

class PaymentSavePortTest {

	@Test
	void 결제저장() {
		// given

		// when

		// then
		Assertions.assertThat(payment.getId()).isPositive();
		Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.OK);
		Assertions.assertThat(payment.getOrder().getStatus()).isEqualTo(OrderStatus.PAYED);
		Assertions.assertThat(payment.getPaymentType()).isEqualTo(paymentType);
		Assertions.assertThat(payment.getCardType()).isEqualTo(cardType);
		Assertions.assertThat(payment.getCardCompany()).isEqualTo(cardCompany);
		Assertions.assertThat(payment.getCardNumber()).isEqualTo(cardNumber);
		Assertions.assertThat(payment.getCardCvc()).isEqualTo(cardCvc);
		Assertions.assertThat(paymentEntity.getId()).isEqualTo(payment.getId());
		Assertions.assertThat(paymentEntity.getPaymentPrice()).isEqualTo(payment.getOrder().getTotalPrice());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.PAYED);



	}

}
