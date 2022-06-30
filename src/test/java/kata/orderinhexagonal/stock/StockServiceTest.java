package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.service.StockService;
import kata.orderinhexagonal.stock.domain.Stock;

@SpringBootTest
class StockServiceTest {

	@Autowired ItemFixture itemFixture;
	@Autowired
	StockService stockService;

	@Autowired
	StockFixture stockFixture;

	@Test
	void stockInTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		int quantity = 1;
		StockInRequest request = StockInRequest.of(item.getId(), quantity);
		// when
		Stock stock = stockService.stockIn(request);
		// then
		Assertions.assertThat(stock.getId()).isPositive();
		Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
		Item stockInItem = stock.getItem();
		Assertions.assertThat(stockInItem.getStockQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stockInItem.getId()).isEqualTo(item.getId());
		Assertions.assertThat(stockInItem.getName()).isEqualTo(item.getName());
	}

	@Test
	void stockOutTest() {
		//given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		int stockOutQuantity = 5;
		int stockInQuantity = 10;
		stockFixture.stockIn(item, stockInQuantity);
		Item stockInItem = itemFixture.getItem(item.getId());
		int currentQuantity = stockInItem.getStockQuantity() - stockOutQuantity;

		// when
		Stock stockOut = stockService.stockOut(item.getId(), stockOutQuantity);
		// then
		Assertions.assertThat(stockOut.getId()).isPositive();
		Assertions.assertThat(stockOut.getStockType()).isEqualTo(Stock.StockType.STOCK_OUT);
		Assertions.assertThat(stockOut.getQuantity()).isEqualTo(stockOutQuantity);
		Assertions.assertThat(stockOut.getItem().getStockQuantity()).isEqualTo(currentQuantity);
		Assertions.assertThat(stockOut.getItem().getId()).isEqualTo(item.getId());
		Assertions.assertThat(stockOut.getItem().getName()).isEqualTo(item.getName());
	}


}
