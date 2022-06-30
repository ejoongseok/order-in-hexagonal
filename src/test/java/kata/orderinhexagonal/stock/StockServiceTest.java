package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.service.StockService;
import kata.orderinhexagonal.stock.domain.Stock;

@SpringBootTest
class StockServiceTest {

	@Autowired ItemFixture itemFixture;
	@Autowired
	StockService stockService;

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

}
