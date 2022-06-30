package kata.orderinhexagonal.stock.application.port.out;

import kata.orderinhexagonal.stock.domain.Stock;

public interface SaveStockPort {
	void save(Stock stock);
}
