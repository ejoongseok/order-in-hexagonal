package kata.orderinhexagonal.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

class PaymentApiTest {

	@Test
	void 주문_결제() {
		// given

		// when

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(paymentResponse.getId()).isPositive();
		Assertions.assertThat(paymentResponse.getOrderId()).isEqualTo(order.getId());
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.PAYED);
		Order getOrder = orderFixture.getOrder(order.getId());
		Assertions.assertThat(paymentResponse.getPaymentPrice()).isEqualTo(getOrder.getTotalPrice());
		Assertions.assertThat(paymentResponse.getPaymentType()).isEqualTo(PaymentType.PAY_IN_FULL);
		Assertions.assertThat(paymentResponse.getCardType()).isEqualTo(CardType.CREDIT_CARD);
	}

}
