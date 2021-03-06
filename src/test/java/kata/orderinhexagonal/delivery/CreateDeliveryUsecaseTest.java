package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.delivery.application.port.in.CreateDeliveryUsecase;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import kata.orderinhexagonal.delivery.domain.Delivery;
import kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
class CreateDeliveryUsecaseTest {

	@Autowired
	CreateDeliveryUsecase createDeliveryUsecase;

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;

	@BeforeEach
	void setUp() {
		orderFixture.clearOrder();
		memberFixture.clearMember();
	}

	@AfterEach
	void tearDown() {
		orderFixture.clearOrder();
		memberFixture.clearMember();
	}



	@Test
	void 배송() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		String location = "옥천 물류센터";
		DeliveryStatus deliveryStatus = DeliveryStatus.SHIPPING;
		DeliveryRequest request = DeliveryRequest.of(order.getId(), deliveryStatus, location);

		// when
		Delivery delivery = createDeliveryUsecase.create(request);
		// then
		Assertions.assertThat(delivery.getId()).isPositive();
		Assertions.assertThat(delivery.getOrder().getId()).isEqualTo(order.getId());
		Assertions.assertThat(delivery.getOrder().getStatus()).isEqualTo(OrderStatus.DELIVERED);
		Assertions.assertThat(delivery.getCreatedDateTime()).isNotNull();
		Assertions.assertThat(delivery.getLocation()).isEqualTo(location);
		Assertions.assertThat(delivery.getStatus()).isEqualTo(deliveryStatus);
	}

}
