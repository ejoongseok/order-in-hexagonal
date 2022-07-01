package kata.orderinhexagonal.stock.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SaveStockAdapter implements SaveStockPort {

	private final StockRepository stockRepository;
	private final StockMapper stockMapper;
	private final LoadItemPort loadItemPort;

	@Override
	@Transactional
	public void save(Stock stock) {
		StockEntity stockEntity = stockMapper.toEntity(stock);
		Item stockItem = stock.getItem();
		ItemEntity itemEntity = loadItemPort.loadEntity(stockItem.getId());
		itemEntity.changeStockQuantity(stockItem.getStockQuantity());
		stockEntity.updateItemEntity(itemEntity);
		stockRepository.save(stockEntity);
		stock.assignId(stockEntity.getId());
	}
}
