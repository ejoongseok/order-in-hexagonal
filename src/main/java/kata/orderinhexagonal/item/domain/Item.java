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

	public Item(Long id, String name, Integer price, Integer stockQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public void assignId(long id) {
		this.id = id;
	}

	public void initializeStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public void stockInQuantity(Integer quantity) {
		this.stockQuantity += quantity;
	}

	public void stockOutQuantity(Integer stockOutQuantity) {
		verifyStockOutQuantityIsGreaterThanCurrentStockQuantity(stockOutQuantity);
		this.stockQuantity -= stockOutQuantity;
	}

	private void verifyStockOutQuantityIsGreaterThanCurrentStockQuantity(Integer stockOutQuantity) {
		if(this.stockQuantity > stockOutQuantity) {
			throw new IllegalArgumentException("재고 수량이 부족합니다.");
		}
	}
}
