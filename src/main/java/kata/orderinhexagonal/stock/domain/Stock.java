package kata.orderinhexagonal.stock.domain;

import kata.orderinhexagonal.item.domain.Item;
import lombok.Getter;

@Getter
public abstract class Stock {
	protected Long id;
	protected Integer quantity;
	protected Item item;

	public Stock(Integer quantity, Item item) {
		this.quantity = quantity;
		this.item = item;
	}

	abstract public StockType getStockType();

	public enum StockType {
		STOCK_IN,
		STOCK_OUT
	}
}
