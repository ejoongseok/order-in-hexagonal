package kata.orderinhexagonal.stock.application.service;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import kata.orderinhexagonal.stock.domain.Stock;
import kata.orderinhexagonal.stock.domain.StockIn;

public class StockService implements StockInUsecase {
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
