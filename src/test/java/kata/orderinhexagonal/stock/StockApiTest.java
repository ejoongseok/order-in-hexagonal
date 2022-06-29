package kata.orderinhexagonal.stock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

class StockApiTest {

	ObjectMapper objectMapper;
	MockMvc mockMvc;

	ItemFixture itemFixture;

	void 상품_입고() throws Exception {
		//given
		String name = "노트북";
		int price = 1_000_000;
		Item item = itemFixture.createItem(name, price);

		Long itemId = item.getId();
		int quantity = 1;
		StockInRequest request = StockInRequest.of(itemId, quantity);

		//when
		MockHttpServletResponse response = mockMvc.perform(post("/stock/in")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andReturn().getResponse();

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		StockInResponse stockInResponse = objectMapper.readValue(response.getContentAsString(), StockInResponse.class);
		Assertions.assertThat(stockInResponse.getId()).isPositive();
		Assertions.assertThat(stockInResponse.getQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stockInResponse.getItemName()).isEqualTo(name);
		Assertions.assertThat(item.getStockQuantity()).isEqualTo(quantity);
	}

	private static class StockInResponse {

		private long id;
		private int quantity;
		private String itemName;

		public long getId() {
			return id;
		}

		public int getQuantity() {
			return quantity;
		}

		public String getItemName() {
			return itemName;
		}
	}
	private static class StockInRequest {
		private final Long itemId;
		private final int quantity;

		public StockInRequest(Long itemId, int quantity) {

			this.itemId = itemId;
			this.quantity = quantity;
		}

		public static StockInRequest of(Long itemId, int quantity) {
			return new StockInRequest(itemId, quantity);
		}
	}

	void 상품_출고() throws Exception {

	}
}
