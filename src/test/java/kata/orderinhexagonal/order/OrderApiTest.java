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
		CreateOrderRequest orderRequest = CreateOrderRequest.of(member, List.of(orderItemRequest1, orderItemRequest2));

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
		주문상품_가격_주문개수_검증(createOrderResponse, 0, orderItem1, orderItem1TotalPrice);
		주문상품_가격_주문개수_검증(createOrderResponse, 1, orderItem2, orderItem2TotalPrice);
		Assertions.assertThat(createOrderResponse.getTotalPrice()).isEqualTo(totalPrice);
		Assertions.assertThat(createOrderResponse.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);
		Assertions.assertThat(createOrderResponse.getMember().getName()).isEqualTo(member.getName());
		상품_남은수량_검증(orderItem1, orderQuantity1, itemFixture.getItem(orderItem1.getId()));
		상품_남은수량_검증(orderItem2, orderQuantity2, itemFixture.getItem(orderItem2.getId()));
	}

	private void 상품_남은수량_검증(Item orderItem1, int orderQuantity1, Item refreshItem1) {
		Assertions.assertThat(refreshItem1.getStockQuantity()).isEqualTo(orderItem1.getStockQuantity() - orderQuantity1);
	}

	private void 주문상품_가격_주문개수_검증(CreateOrderResponse createOrderResponse, int index, Item orderItem,
		int orderItemTotalPrice) {
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getQuantity()).isEqualTo(orderItem.getStockQuantity());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getItemId()).isEqualTo(orderItem.getId());
		Assertions.assertThat(createOrderResponse.getOrderItems().get(index).getPrice()).isEqualTo(orderItemTotalPrice);
	}

}
