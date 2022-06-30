package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import kata.orderinhexagonal.stock.domain.Stock;
import kata.orderinhexagonal.stock.domain.StockIn;

@SpringBootTest
class SaveStockPortTest {

	@Autowired
	ItemFixture itemFixture;
	@Autowired SaveStockPort saveStockPort;

	@Test
	void saveStockTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		int quantity = 1;
		Stock stock = new StockIn(quantity, item);
		// when
	    saveStockPort.save(stock);
		// then
		Assertions.assertThat(stock.getId()).isPositive();
		Assertions.assertThat(stock.getQuantity()).isEqualTo(quantity);
		Assertions.assertThat(stock.getStockType()).isEqualTo(Stock.StockType.STOCK_IN);
	}

}
