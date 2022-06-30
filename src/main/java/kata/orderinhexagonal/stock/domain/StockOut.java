package kata.orderinhexagonal.stock.domain;

import kata.orderinhexagonal.item.domain.Item;

public class StockOut extends Stock {
	public StockOut(Integer stockOutQuantity, Item item) {
		super(stockOutQuantity, item);
	}

	@Override
	public StockType getStockType() {
		return StockType.STOCK_OUT;
	}
}
