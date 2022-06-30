package kata.orderinhexagonal.item.domain;

import lombok.Getter;

@Getter
public class Item {
	private Long id;
	private String name;
	private Integer price;
	private Integer stockQuantity;

	public Item(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public void assignId(long id) {
		this.id = id;
	}

	public void initializeStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void stockIn(Integer quantity) {
		this.stockQuantity += quantity;
	}
}
