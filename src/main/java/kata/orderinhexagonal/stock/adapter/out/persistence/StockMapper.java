package kata.orderinhexagonal.stock.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StockMapper {

	private final ItemMapper itemMapper;
	public StockEntity toEntity(Stock stock) {
		return new StockEntity(stock.getQuantity(), itemMapper.toEntity(stock.getItem()), stock.getStockType());
	}
}
