package kata.orderinhexagonal.stock.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.stock.domain.Stock;

@Component
public class StockMapper {
	public StockEntity toEntity(Stock stock) {
		return new StockEntity(stock.getId(), stock.getQuantity(), stock.getStockType());
	}
}
