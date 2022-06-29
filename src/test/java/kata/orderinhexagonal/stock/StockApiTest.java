package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;

class StockApiTest {

	void 상품_입고() throws Exception {
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		Assertions.assertThat(warehousingResponse.getId()).isPositive();
		Assertions.assertThat(warehousingResponse.getQuantity()).isEqaulTo(quantity);
		Assertions.assertThat(item.getStockQuantity()).isEqaulTo(quantity);
	}

	void 상품_출고() throws Exception {

	}
}
