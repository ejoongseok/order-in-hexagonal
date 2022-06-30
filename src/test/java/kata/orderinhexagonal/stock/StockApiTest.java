package kata.orderinhexagonal.stock;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

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
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInResponse;

@SpringBootTest
@AutoConfigureMockMvc
class StockApiTest {

	@Autowired ObjectMapper objectMapper;
	@Autowired MockMvc mockMvc;

	@Autowired ItemFixture itemFixture;

	@Test
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
		Assertions.assertThat(stockInResponse.getItemId()).isEqualTo(item.getId());
		Assertions.assertThat(stockInResponse.getItemName()).isEqualTo(name);
	}

	void 상품_출고() throws Exception {
		//given
		int stockInQuantity = 10;
		int stockOutQuantity = 5;
		Item item = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item, stockInQuantity);
		StockOutRequest request = StockOutRequest.of(item.getId(), stockOutQuantity);
		int currentQuantity = stockInQuantity - stockOutQuantity;
		//when
		MockHttpServletResponse response = mockMvc.perform(post("/stock/out")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andReturn().getResponse();

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		Assertions.assertThat(stockOutResponse.getId()).isPositive();
		Assertions.assertThat(stockOutResponse.getQuantity()).isEqualTo(stockOutQuantity);
		Assertions.assertThat(stockOutResponse.getItemId()).isEqualTo(item.getId());
		Assertions.assertThat(stockOutResponse.getItemName()).isEqualTo(item.getName());
		Item refreshItem = itemFixture.getItem(item.getId());
		Assertions.assertThat(refreshItem.getStockQuantity()).isEqualTo(currentQuantity);
	}
}
