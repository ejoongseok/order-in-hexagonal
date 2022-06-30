package kata.orderinhexagonal.stock.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import kata.orderinhexagonal.stock.domain.Stock;
import kata.orderinhexagonal.stock.domain.StockIn;
import kata.orderinhexagonal.stock.domain.StockOut;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockService implements StockInUsecase, StockOutUsecase {
	private final LoadItemPort loadItemPort;
	private final SaveStockPort saveStockPort;

	@Override
	public Stock stockIn(StockInRequest request) {
		Item item = loadItemPort.load(request.getItemId());
		Integer stockInQuantity = request.getStockInQuantity();
		item.stockInQuantity(stockInQuantity);
		Stock stock = new StockIn(stockInQuantity, item);
		saveStockPort.save(stock);
		return stock;
	}

	@Override
	public Stock stockOut(StockOutRequest request) {
		Item item = loadItemPort.load(request.getItemId());
		Integer stockOutQuantity = request.getStockOutQuantity();
		item.stockOutQuantity(stockOutQuantity);
		Stock stock = new StockOut(stockOutQuantity, item);
		saveStockPort.save(stock);
		return stock;
	}
}
