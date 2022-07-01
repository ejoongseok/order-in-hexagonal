package kata.orderinhexagonal.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

class OrderApiTest {

	MockMvc mockMvc;
	ObjectMapper objectMapper;

	ItemFixture itemFixture;

	StockFixture stockFixture;

	MemberFixture memberFixture;

	@Test
	void 상품_주문() throws Exception {
		// given
		Item orderItem1 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(orderItem1, 10);
		Item orderItem2 = itemFixture.createItem("가방", 150_000);
		stockFixture.stockIn(orderItem2, 10);

		String name = "이중석";
		String email = "ejoongseok@gmail.com";
		String location = "대전광역시 서구";
		Member member = memberFixture.createMember(name, email, location);

		int orderQuantity1 = 1;
		int orderQuantity2 = 3;
		int orderItem1TotalPrice = orderQuantity1 * orderItem1.getPrice();
		int orderItem2TotalPrice = orderQuantity2 * orderItem2.getPrice();
		int totalPrice = orderItem1TotalPrice + orderItem2TotalPrice;

		OrderItemRequest orderItemRequest1 = OrderItemRequest.of(orderItem1.getId(), orderQuantity1);
		OrderItemRequest orderItemRequest2 = OrderItemRequest.of(orderItem2.getId(), orderQuantity2);
		CreateOrderRequest orderRequest = CreateOrderRequest.of(List.of(orderItemRequest1, orderItemRequest2));

		// when
		MockHttpServletResponse response = mockMvc.perform(post("/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(orderRequest))
		).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		CreateOrderResponse createOrderResponse = objectMapper.readValue(response.getContentAsString(),
			CreateOrderResponse.class);
		Assertions.assertThat(createOrderResponse.getId()).isPositive();
		Assertions.assertThat(createOrderResponse.getOrderItems()).hasSize(2);
		Assertions.assertThat(createOrderResponse.getOrderItems().get(0).getQuantity()).isEqualTo(orderItem1.getStockQuantity());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(0).getItemId()).isEqualTo(orderItem1.getId());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(0).getPrice()).isEqualTo(orderItem1TotalPrice);
		Assertions.assertThat(createOrderResponse.getOrderItems().get(1).getQuantity()).isEqualTo(orderItem2.getStockQuantity());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(1).getItemId()).isEqualTo(orderItem2.getId());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(1).getPrice()).isEqualTo(orderItem2TotalPrice);
		Assertions.assertThat(createOrderResponse.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(createOrderResponse.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(createOrderResponse.getMember().getName()).isEqualTo(member.getName());
		Item refreshItem1 = itemFixture.getItem(orderItem1.getId());
		Item refreshItem2 = itemFixture.getItem(orderItem2.getId());
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(orderItem1.getStockQuantity() - orderQuantity1);
		Assertions.assertThat(refreshItem2.getStockQuantity()).isEqualTo(orderItem2.getStockQuantity() - orderQuantity2);
	}

	private static class OrderItemResponse {
		private long itemId;
		private int quantity;
		private int price;

		public long getItemId() {
			return itemId;
		}

		public int getQuantity() {
			return quantity;
		}

		public int getPrice() {
			return price;
		}
	}

	private class MemberFixture {
		CreateMemberUsecase createMemberUsecase;
		public Member createMember(String name, String email, String location) {
			CreateMemberRequest request = new CreateMemberRequest(name, "ejoongseok1234!", email, location);
			Member member = createMemberUsecase.join(request);
			return member;
		}
	}

	private static class OrderItemRequest {
		private Long id;
		private int orderQuantity;

		public OrderItemRequest(Long id, int orderQuantity) {
			this.id = id;
			this.orderQuantity = orderQuantity;
		}

		public static OrderItemRequest of(Long id, int orderQuantity) {
			return new OrderItemRequest(id, orderQuantity);
		}
	}

	private static class CreateOrderRequest {
		List<OrderItemRequest> orderItemRequests = new ArrayList<>();
		public CreateOrderRequest(List<OrderItemRequest> orderItemRequests) {
			this.orderItemRequests = orderItemRequests;
		}

		public static CreateOrderRequest of(List<OrderItemRequest> orderItemRequests) {
			return new CreateOrderRequest(orderItemRequests);
		}
	}

	private static class CreateOrderResponse {
		private Long id;
		private List<OrderItemResponse> orderItems = new ArrayList<>();
		private int totalPrice;
		private OrderStatus status;
		private Member member;

		public Long getId() {
			return id;
		}

		public List<OrderItemResponse> getOrderItems() {
			return orderItems;
		}

		public int getTotalPrice() {
			return totalPrice;
		}

		public OrderStatus getStatus() {
			return status;
		}

		public Member getMember() {
			return member;
		}
	}

	public enum OrderStatus {
		NOT_PAYED,
		PAYED,
		CANCELED,
		DELIVERED
	}
}
