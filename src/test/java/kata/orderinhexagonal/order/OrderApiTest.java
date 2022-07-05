package kata.orderinhexagonal.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

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

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.fixture.OrderItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.application.port.in.CreateMemberResponse;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderItemEntity;
import kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CancelOrderResponse;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderResponse;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Autowired ItemFixture itemFixture;

	@Autowired StockFixture stockFixture;

	@Autowired MemberFixture memberFixture;

	@Autowired
	OrderItemFixture orderItemFixture;

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
	void 주문_취소() throws Exception {
		// given
		MockHttpServletResponse createMemberAndGetAccessToken = craeteMemberAndGetAccessToken();
		CreateMemberResponse createMemberResponse = objectMapper.readValue(createMemberAndGetAccessToken.getContentAsString(),
			CreateMemberResponse.class);
		Order order = orderFixture.createOrder(createMemberResponse.getId());
		CancelOrderRequest request = CancelOrderRequest.of(order.getId());

		// when
		MockHttpServletResponse response = mockMvc.perform(delete("/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
			.header("Authorization", createMemberAndGetAccessToken.getHeader("Authorization"))
		).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		CancelOrderResponse cancelOrderResponse = objectMapper.readValue(response.getContentAsString(), CancelOrderResponse.class);
		Assertions.assertThat(cancelOrderResponse.getOrderId()).isEqualTo(order.getId());
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.CANCELED);
	}




	@Test
	void 상품_주문() throws Exception {
		// given
		Item item1 = itemFixture.createItem("노트북", 1_000_000);
		int stockInQuantity1 = 10;
		stockFixture.stockIn(item1, stockInQuantity1);
		item1.stockInQuantity(stockInQuantity1);
		Item item2 = itemFixture.createItem("가방", 150_000);
		int stockInQuantity2 = 10;
		stockFixture.stockIn(item2, stockInQuantity2);
		item2.stockInQuantity(stockInQuantity2);

		MockHttpServletResponse createMemberResponse = craeteMemberAndGetAccessToken();
		String accessToken = createMemberResponse.getHeader("Authorization");

		int orderQuantity1 = 1;
		int orderQuantity2 = 3;
		int orderItem1TotalPrice = orderQuantity1 * item1.getPrice();
		int orderItem2TotalPrice = orderQuantity2 * item2.getPrice();
		int totalPrice = orderItem1TotalPrice + orderItem2TotalPrice;

		OrderItemRequest orderItemRequest1 = OrderItemRequest.of(item1.getId(), orderQuantity1);
		OrderItemRequest orderItemRequest2 = OrderItemRequest.of(item2.getId(), orderQuantity2);
		CreateOrderRequest orderRequest = CreateOrderRequest.of(List.of(orderItemRequest1, orderItemRequest2));

		// when
		MockHttpServletResponse response = mockMvc.perform(post("/orders")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(orderRequest))
			.header("Authorization", accessToken)
		).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		CreateOrderResponse createOrderResponse = objectMapper.readValue(response.getContentAsString(),
			CreateOrderResponse.class);
		Assertions.assertThat(createOrderResponse.getId()).isPositive();
		Assertions.assertThat(createOrderResponse.getOrderItems()).hasSize(2);
		List<OrderItemEntity> orderItems = orderItemFixture.getOrderItems(createOrderResponse.getId());
		주문상품_가격_주문개수_검증(createOrderResponse, 0, orderItems, orderItem1TotalPrice);
		주문상품_가격_주문개수_검증(createOrderResponse, 1, orderItems, orderItem2TotalPrice);
		Assertions.assertThat(createOrderResponse.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(createOrderResponse.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		상품_남은수량_검증(item1, orderQuantity1, itemFixture.getItem(item1.getId()));
		상품_남은수량_검증(item2, orderQuantity2, itemFixture.getItem(item2.getId()));
	}

	private MockHttpServletResponse craeteMemberAndGetAccessToken() throws Exception {
		String name = "이중석";
		String email = "ejoongseok@gmail.com";
		String location = "대전광역시 서구";
		MockHttpServletResponse response = memberFixture.getAccessToken(name, email, location, mockMvc,
			objectMapper);
		return response;
	}

	private void 상품_남은수량_검증(Item orderItem1, int orderQuantity1, Item refreshItem1) {
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(orderItem1.getStockQuantity() - orderQuantity1);
	}

	private void 주문상품_가격_주문개수_검증(CreateOrderResponse createOrderResponse, int index, List<OrderItemEntity> orderItems,
		int orderItemTotalPrice) {
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getQuantity()).isEqualTo(orderItems.get(index).getOrderQuantity());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getItemId()).isEqualTo(orderItems.get(index).getId());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getPrice()).isEqualTo(orderItemTotalPrice);
	}

}
