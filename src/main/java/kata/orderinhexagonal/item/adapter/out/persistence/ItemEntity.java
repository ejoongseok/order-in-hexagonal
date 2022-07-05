package kata.orderinhexagonal.item.adapter.out.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import kata.orderinhexagonal.discount.adapter.out.persistence.DiscountEntity;
import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.item.domain.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntity {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private Integer price;
	@Column(nullable = false)
	private Integer stockQuantity;

	@OneToOne(mappedBy = "item", fetch = FetchType.LAZY)
	private DiscountEntity discount;

	public ItemEntity(Long id, String name, Integer price, Integer stockQuantity, DiscountEntity toEntity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.discount = toEntity;
	}

	public ItemEntity(Long id, String name, Integer price, Integer stockQuantity, Discount discount) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
		if(discount != null) {
			this.discount = new DiscountEntity(discount.getId(), discount.getDiscountType(), new Item(this.id, this.name, this.price, this.stockQuantity, discount), discount.getDiscountValue());
		}
	}

	public void changeStockQuantity(Integer quantity) {
		this.stockQuantity = quantity;
	}
}
