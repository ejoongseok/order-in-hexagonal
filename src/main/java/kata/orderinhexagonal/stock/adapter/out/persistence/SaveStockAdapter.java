package kata.orderinhexagonal.stock.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveStockAdapter implements SaveStockPort {

	private final StockRepository stockRepository;
	private final StockMapper stockMapper;

	@Override
	public void save(Stock stock) {
		StockEntity stockEntity = stockMapper.toEntity(stock);
		stockRepository.save(stockEntity);
	}
}
