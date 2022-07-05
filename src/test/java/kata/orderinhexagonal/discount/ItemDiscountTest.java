package kata.orderinhexagonal.discount;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.io.UnsupportedEncodingException;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountResponse;
import kata.orderinhexagonal.discount.domain.DiscountType;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
@AutoConfigureMockMvc
class ItemDiscountTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Autowired ItemFixture itemFixture;

	@Test
	void 상품_할인_등록() throws Exception {
		// given
		int discountRate = 10;
		int price = 1_000_000;
		String name = "노트북";
		Item item1 = itemFixture.createItem(name, price);
		CreateItemDiscountRequest request1 = CreateItemDiscountRequest.of(item1.getId(), DiscountType.RATE, discountRate);
		Item item2 = itemFixture.createItem(name, price);
		CreateItemDiscountRequest request2 = CreateItemDiscountRequest.of(item2.getId(), DiscountType.FIXED, discountRate);
		// when
		MockHttpServletResponse response1 = apiCall(item1, request1);
		MockHttpServletResponse response2 = apiCall(item2, request2);

		// then
		응답검증(discountRate, DiscountType.RATE, item1, response1);
		응답검증(discountRate, DiscountType.FIXED, item2, response2);
	}

	private MockHttpServletResponse apiCall(Item item, CreateItemDiscountRequest request) throws
		Exception {
		MockHttpServletResponse response = mockMvc.perform(post("/discounts/items", item.getId())
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();
		return response;
	}

	private void 응답검증(int discountRate,DiscountType discountType, Item item, MockHttpServletResponse response) throws
		JsonProcessingException,
		UnsupportedEncodingException {
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		CreateItemDiscountResponse createItemDiscountResponse = objectMapper.readValue(response.getContentAsString(),
			CreateItemDiscountResponse.class);
		Assertions.assertThat(createItemDiscountResponse.getId()).isPositive();
		Assertions.assertThat(createItemDiscountResponse.getItemId()).isEqualTo(item.getId());
		Assertions.assertThat(createItemDiscountResponse.getDiscountType()).isEqualTo(discountType);
		Assertions.assertThat(createItemDiscountResponse.getDiscountRate()).isEqualTo(discountRate);
	}
}
