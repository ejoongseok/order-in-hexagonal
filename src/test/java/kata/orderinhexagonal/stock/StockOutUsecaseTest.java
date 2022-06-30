package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import kata.orderinhexagonal.stock.domain.Stock;

@SpringBootTest
class StockOutUsecaseTest {

	@Autowired ItemFixture itemFixture;

	@Autowired StockFixture stockFixture;

	@Autowired StockOutUsecase stockOutUsecase;

	@Test
	void 상품_출고() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		int stockOutQuantity = 5;
		int stockInQuantity = 10;
		stockFixture.stockIn(item, stockInQuantity);
		Item stockInItem = itemFixture.getItem(item.getId());
		int currentQuantity = stockInItem.getStockQuantity() - stockOutQuantity;
		StockOutRequest request = StockOutRequest.of(item.getId(), stockOutQuantity);

		// when
		Stock stockOut = stockOutUsecase.stockOut(request);
		// then
		Assertions.assertThat(stockOut.getId()).isPositive();
		Assertions.assertThat(stockOut.getStockType()).isEqualTo(Stock.StockType.STOCK_OUT);
		Assertions.assertThat(stockOut.getQuantity()).isEqualTo(stockOutQuantity);
		Assertions.assertThat(stockOut.getItem().getStockQuantity()).isEqualTo(currentQuantity);
		Assertions.assertThat(stockOut.getItem().getId()).isEqualTo(item.getId());
		Assertions.assertThat(stockOut.getItem().getName()).isEqualTo(item.getName());
	}

}
