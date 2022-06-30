package kata.orderinhexagonal.stock;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import kata.orderinhexagonal.stock.domain.Stock;

class StockServiceTest {

	ItemFixture itemFixture;
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

	private static class StockService implements StockInUsecase {
		LoadItemPort loadItemPort;
		SaveStockPort saveStockPort;
		@Override
		public Stock stockIn(StockInRequest request) {
			Item item = loadItemPort.load(request.getItemId());
			item.stockIn(request.getQuantity());
			Stock stock = new StockIn(request.getQuantity(), item);
			saveStockPort.save(stock);
			return stock;
		}

	}
	public static class StockIn extends Stock {
		public StockIn(int quantity, Item item) {
			super(quantity, item);
		}

		@Override
		public StockType getStockType() {
			return StockType.STOCK_IN;
		}
	}
}
