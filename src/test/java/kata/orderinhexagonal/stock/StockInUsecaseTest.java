package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import kata.orderinhexagonal.stock.domain.Stock;

class StockInUsecaseTest {

	ItemFixture itemFixture;
	StockInUsecase stockInUsecase;

	@Test
	void 상품_입고() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		int quantity = 1;
		StockInRequest request = StockInRequest.of(item.getId(), quantity);

		// when
		Stock stock = stockInUsecase.stockIn(request);
		// then
		Assertions.assertThat(stock.getId()).isPositive();
		Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
		Item stockInItem = stock.getItem();
		Assertions.assertThat(stockInItem.getStockQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stockInItem.getId()).isEqualTo(item.getId());
		Assertions.assertThat(stockInItem.getName()).isEqualTo(item.getName());
	}

}
