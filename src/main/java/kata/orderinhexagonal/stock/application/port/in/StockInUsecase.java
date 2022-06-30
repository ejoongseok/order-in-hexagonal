package kata.orderinhexagonal.stock.application.port.in;

import kata.orderinhexagonal.stock.domain.Stock;

public interface StockInUsecase {
	Stock stockIn(StockInRequest request);
}
