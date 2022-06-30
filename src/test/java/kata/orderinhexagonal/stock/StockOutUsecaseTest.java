package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.stock.domain.Stock;

class StockOutUsecaseTest {

	@Test
	void 상품_출고() {
		// given

		// when

		// then
		Assertions.assertThat(stockOut.getId()).isPositive();
		Assertions.assertThat(stockOut.getStockType()).isEqualTo(Stock.StockType.STOCK_OUT);
		Assertions.assertThat(stockOut.getQuantity()).isEqualTo(stockOutQuantity);
		Assertions.assertThat(stockOut.getItem().getStockQuantity()).isEqualTo(currentQuantity);
		Assertions.assertThat(stockOut.getItem().getId()).isEqualTo(item.getId());
		Assertions.assertThat(stockOut.getItem().getName()).isEqualTo(item.getName());
	}

}
