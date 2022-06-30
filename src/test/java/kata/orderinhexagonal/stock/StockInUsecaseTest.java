package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StockInUsecaseTest {

	@Test
	void 상품_입고() {
		// given

		// when

		// then
		Assertions.assertThat(stock.getId()).isPositive();
		Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stockInItem.getStockQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stockInItem.getId()).isEqualTo(itemId);
		Assertions.assertThat(stockInItem.getName()).isEqualTo(itemName);
	}

}
