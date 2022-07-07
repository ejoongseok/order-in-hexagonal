package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class DeliveryApiTest {

	@Test
	void 주문_배송_시작() {
		// given

		// when

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(deliveryResponse.getId()).isPositive();
		Assertions.assertThat(deliveryResponse.getStatus()).isEqualTo(DeliveryStatus.STARTED);
		Assertions.assertThat(deliveryResponse.getLocation()).isEqualTo(startedLocation);
		Assertions.assertThat(deliveryResponse.getCreatedDateTime()).NotNull();
		Assertions.assertThat(deliveryResponse.getOrderId()).isEqualTo(order.getId());
	}

}
