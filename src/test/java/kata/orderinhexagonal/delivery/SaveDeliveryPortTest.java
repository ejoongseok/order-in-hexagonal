package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SaveDeliveryPortTest {

	@Test
	void 배송정보저장() {
		// given

		// when

		// then
		Assertions.assertThat(delivery.getId()).isPositive();
		Assertions.assertThat(delivery.getStatus()).isEqualTo(deliveryStatus);
		Assertions.assertThat(delivery.getLocation).isEqualTo(location);
		Assertions.assertThat(deliveryEntity.getId()).isEqualTo(delivery.getId());
		Assertions.assertThat(deliveryEntity.getStatus()).isEqualTo(delivery.getStatus());
		Assertions.assertThat(deliveryEntity.getLocation()).isEqualTo(delivery.getLocation());
		Assertions.assertThat(orderDelivery.getOrder.getId()).isEqualTo(order.getId());
		Assertions.assertThat(orderDelivery.getOrder.getDelivery.getId()).isEqualTo(delivery.getId());
	}

}
