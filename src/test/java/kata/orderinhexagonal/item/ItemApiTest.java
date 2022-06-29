package kata.orderinhexagonal.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ItemApiTest {

	@Test
	void 상품_등록() {
		// given

		// when

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		Assertions.assertThat(createItemResponse.getId()).isPositive();
		Assertions.assertThat(createItemResponse.getName()).isEqualTo(name);
		Assertions.assertThat(createItemResponse.getPrice()).isEqualTo(price);
		Assertions.assertThat(createItemResponse.getStockQuantity()).isEqualTo(stockQuantity);
	}

}
