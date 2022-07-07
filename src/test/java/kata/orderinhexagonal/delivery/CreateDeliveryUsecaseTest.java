package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.order.domain.OrderStatus;

class CreateDeliveryUsecaseTest {

	@Test
	void 배송() {
		// given

		// when

		// then
		Assertions.assertThat(delivery.getId()).isPositive();
		Assertions.assertThat(delivery.getOrder().getId()).isEqualTo(order.getId());
		Assertions.assertThat(delivery.getOrder().getStatus()).isEqualTo(OrderStatus.DELIVERED);
		Assertions.assertThat(delivery.getCreatedDateTime()).isNotNull();
		Assertions.assertThat(delivery.getLocation()).isEqualTo(location);
		Assertions.assertThat(delivery.getStatus()).isEqualTo(deliveryStatus);
	}

}
