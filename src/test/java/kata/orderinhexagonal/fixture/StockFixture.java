package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;

@Component
public class StockFixture {

	@Autowired StockInUsecase stockInUsecase;

	public void stockIn(Item item, int stockInQuantity) {
		stockInUsecase.stockIn(StockInRequest.of(item.getId(), stockInQuantity));
	}
}
