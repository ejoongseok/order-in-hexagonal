package kata.orderinhexagonal.stock.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	public StockEntity(Long id, Integer quantity, Stock.StockType stockType) {
		this.id = id;
		this.quantity = quantity;
		this.stockType = stockType;
	}

	public void updateItemEntity(ItemEntity itemEntity) {
		this.itemEntity = itemEntity;
	}
}
