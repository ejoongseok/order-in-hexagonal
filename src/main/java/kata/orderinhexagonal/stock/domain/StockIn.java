package kata.orderinhexagonal.stock.domain;

import kata.orderinhexagonal.item.domain.Item;

public class StockIn extends Stock {
	public StockIn(int quantity, Item item) {
		super(quantity, item);
	}

	@Override
	public StockType getStockType() {
		return StockType.STOCK_IN;
	}
}
