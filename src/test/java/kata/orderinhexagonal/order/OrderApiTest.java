package kata.orderinhexagonal.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
		Item item1 = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item1, 10);
		Item item2 = itemFixture.createItem("가방", 150_000);
		stockFixture.stockIn(item2, 10);

		Member member = memberFixture.createMember(name, email, location);

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
		).andReturn().andReponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(CreateOrderResponse.getId()).isPositive();
		Assertions.assertThat(CreateOrderResponse.getOrderItems()).isSize(2);
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(0).getQuantity()).isEqualTo(orderItem1.getQuantity());
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(0).getPrice()).isEqualTo(orderItem1TotalPrice);
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(1).getQuantity()).isEqualTo(orderItem2.getQuantity());
		Assertions.assertThat(CreateOrderResponse.getOrderItems().get(1).getPrice()).isEqualTo(orderItem2TotalPrice);
		Assertions.assertThat(CreateOrderResponse.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(CreateOrderResponse.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(CreateOrderResponse.getMember().getName()).isEqualTo(member.getName());
		Item refreshItem1 = itemFixture.getItem(item1.getId());
		Item refreshItem2 = itemFixture.getItem(item2.getId());
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(item1.getStockQuantity() - orderQuantity1);
		Assertions.assertThat(refreshItem2.getStockQuantity()).isEqualTo(item2.getStockQuantity() - orderQuantity2);
	}

}
