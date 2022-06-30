package kata.orderinhexagonal.fixture;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;

public class StockFixture {

	StockInUsecase stockInUsecase;

	public void stockIn(Item item, int stockInQuantity) {
		stockInUsecase.stockIn(StockInRequest.of(item.getId(), stockInQuantity));
	}
}
