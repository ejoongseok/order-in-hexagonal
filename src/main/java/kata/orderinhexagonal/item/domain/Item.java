package kata.orderinhexagonal.item.domain;

import kata.orderinhexagonal.discount.adapter.out.persistence.DiscountEntity;
import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import lombok.Getter;

@Getter
public class Item {
	private Long id;
	private String name;
	private Integer price;
	private Integer stockQuantity;

	private Discount discount;

	public Item(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public Item(Long id, String name, Integer price, Integer stockQuantity, Discount discount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.discount = discount;
	}

	public Item(Long id, String name, Integer price, Integer stockQuantity, DiscountEntity discount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		if(discount != null) {
			this.discount = new Discount(discount.getId(), new ItemEntity(this.id, this.name, this.price, this.stockQuantity, discount), discount.getDiscountType(), discount.getDiscountValue());
		}
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
		if(this.stockQuantity < stockOutQuantity) {
			throw new IllegalArgumentException("재고 수량이 부족합니다.");
		}
	}
}
