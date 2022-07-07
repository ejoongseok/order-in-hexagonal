package kata.orderinhexagonal.delivery;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.delivery.domain.Delivery;
import kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import kata.orderinhexagonal.discount.adapter.out.persistence.CreateDiscountAdapter;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;

@SpringBootTest
class SaveDeliveryPortTest {

	@Autowired
	MemberFixture memberFixture;

	@Autowired
	OrderFixture orderFixture;

	@Autowired
	SaveDeliveryPort saveDeliveryPort;

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
	void 배송정보저장() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		DeliveryStatus deliveryStatus = DeliveryStatus.SHIPPING;
		String location = "옥천 물류센터";
		Delivery delivery = new Delivery(order, deliveryStatus, location);

		// when
		saveDeliveryPort.save(delivery);

		// then
		Assertions.assertThat(delivery.getId()).isPositive();
		Assertions.assertThat(delivery.getStatus()).isEqualTo(deliveryStatus);
		Assertions.assertThat(delivery.getLocation()).isEqualTo(location);
		DeliveryEntity deliveryEntity = deliveryFixture.getDeliveryEntity(delivery.getId());
		Assertions.assertThat(deliveryEntity.getId()).isEqualTo(delivery.getId());
		Assertions.assertThat(deliveryEntity.getStatus()).isEqualTo(delivery.getStatus());
		Assertions.assertThat(deliveryEntity.getLocation()).isEqualTo(delivery.getLocation());
		List<OrderDeliveryEntity> orderDeliveryList =  deliveryFixture.getOrderDeliveryList(order.getId());
		OrderDeliveryEntity orderDelivery = orderDeliveryList.get(0);
		Assertions.assertThat(orderDelivery.getOrder().getId()).isEqualTo(order.getId());
		Assertions.assertThat(orderDelivery.getDelivery().getId()).isEqualTo(delivery.getId());
	}

}
