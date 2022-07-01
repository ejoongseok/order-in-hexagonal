package kata.orderinhexagonal.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;

import org.assertj.core.api.Assertions;
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
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderResponse;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Autowired ItemFixture itemFixture;

	@Autowired StockFixture stockFixture;

	@Autowired MemberFixture memberFixture;

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

}
