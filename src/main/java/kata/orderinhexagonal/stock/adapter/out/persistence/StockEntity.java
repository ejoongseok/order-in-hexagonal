package kata.orderinhexagonal.stock.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockEntity {

	@Id
	@GeneratedValue
	private Long id;
	private Integer quantity;
	@Enumerated(EnumType.STRING)
	private Stock.StockType stockType;
	@ManyToOne
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	public StockEntity(Integer quantity, ItemEntity itemEntity, Stock.StockType stockType) {
		this.quantity = quantity;
		this.itemEntity = itemEntity;
		this.stockType = stockType;
	}
}
