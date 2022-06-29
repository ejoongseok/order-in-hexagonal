package kata.orderinhexagonal.item.domain;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;

public class Item {
	private Long id;
	private String name;
	private Integer price;
	private Integer stockQuantity;

	public Item(String name, Integer price) {
		this.name = name;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getStockQuantity() {
		return stockQuantity;
	}

	public void assignId(long id) {
		this.id = id;
	}
}
