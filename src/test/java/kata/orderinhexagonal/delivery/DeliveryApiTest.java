package kata.orderinhexagonal.delivery;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.domain.Order;

@SpringBootTest
@AutoConfigureMockMvc
class DeliveryApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
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
	void 주문_배송_시작() throws Exception {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());

		String startedLocation = "옥천 물류센터";
		DeliveryRequest request = DeliveryRequest.of(order.getId(), DeliveryStatus.SHIPPING, startedLocation);

		// when
		MockHttpServletResponse response = mockMvc.perform(post("/delivery")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		DeliveryResponse deliveryResponse = objectMapper.readValue(response.getContentAsString(),
			DeliveryResponse.class);
		Assertions.assertThat(deliveryResponse.getId()).isPositive();
		Assertions.assertThat(deliveryResponse.getStatus()).isEqualTo(DeliveryStatus.SHIPPING);
		Assertions.assertThat(deliveryResponse.getLocation()).isEqualTo(startedLocation);
		Assertions.assertThat(deliveryResponse.getCreatedDateTime()).isNotNull();
		Assertions.assertThat(deliveryResponse.getOrderId()).isEqualTo(order.getId());
	}

	private static class DeliveryResponse {
		private Long id;
		private DeliveryStatus status;
		private String location;
		private LocalDateTime createdDateTime;
		private Long orderId;

		public Long getId() {
			return id;
		}

		public DeliveryStatus getStatus() {
			return status;
		}

		public String getLocation() {
			return location;
		}

		public LocalDateTime getCreatedDateTime() {
			return createdDateTime;
		}

		public Long getOrderId() {
			return orderId;
		}
	}

	public enum DeliveryStatus {
		SHIPPING, DELIVERED
	}

	private static class DeliveryRequest {
		private Long orderId;
		private DeliveryStatus deliveryStatus;
		private String location;

		public DeliveryRequest(Long orderId, DeliveryStatus deliveryStatus, String location) {
			this.orderId = orderId;
			this.deliveryStatus = deliveryStatus;
			this.location = location;
		}

		public static DeliveryRequest of(Long orderId, DeliveryStatus deliveryStatus, String location) {
			return new DeliveryRequest(orderId, deliveryStatus, location);
		}

		public Long getOrderId() {
			return orderId;
		}

		public DeliveryStatus getDeliveryStatus() {
			return deliveryStatus;
		}

		public String getLocation() {
			return location;
		}
	}
}
