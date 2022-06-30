package kata.orderinhexagonal.stock.application.port.in;

import kata.orderinhexagonal.stock.domain.Stock;

public interface StockOutUsecase {
	Stock stockOut(Long id, int stockOutQuantity);
}
