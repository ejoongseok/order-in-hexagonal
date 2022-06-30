package kata.orderinhexagonal.stock.domain;

import kata.orderinhexagonal.item.domain.Item;

public class Stock {
	private Long id;
	private Integer quantity;
	private Item item;

	public Long getId() {
		return id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Item getItem() {
		return item;
	}
}
