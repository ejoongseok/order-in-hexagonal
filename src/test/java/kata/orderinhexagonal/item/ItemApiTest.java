package kata.orderinhexagonal.item;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class ItemApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Test
	void 상품_등록() throws Exception {
		// given
		String name = "노트북";
		int price = 1_000_000;
		CreateItemRequest request = CreateItemRequest.of(name, price);

		// when
		MockHttpServletResponse response = mockMvc.perform(post("/items")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();
		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		CreateItemResponse createItemResponse = objectMapper.readValue(response.getContentAsString(), CreateItemResponse.class);
		Assertions.assertThat(createItemResponse.getId()).isPositive();
		Assertions.assertThat(createItemResponse.getName()).isEqualTo(name);
		Assertions.assertThat(createItemResponse.getPrice()).isEqualTo(price);
		Assertions.assertThat(createItemResponse.getStockQuantity()).isZero();
	}

	private static class CreateItemResponse {
		private long id;
		private String name;
		private int price;
		private int stockQuantity;

		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public int getPrice() {
			return price;
		}

		public int getStockQuantity() {
			return stockQuantity;
		}
	}

	private static class CreateItemRequest {
		private String name;
		private int price;

		public CreateItemRequest(String name, int price) {
			this.name = name;
			this.price = price;
		}

		public static CreateItemRequest of(String name, int price) {
			return new CreateItemRequest(name, price);
		}
	}
}
