package kata.orderinhexagonal.discount.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import kata.orderinhexagonal.discount.domain.DiscountType;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private DiscountType discountType;


	@OneToOne(fetch = javax.persistence.FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private ItemEntity itemEntity;

	private int discountValue;

	public DiscountEntity(Long id, DiscountType discountType, ItemEntity toEntity, int discountValue) {
		this.id = id;
		this.discountType = discountType;
		this.itemEntity = toEntity;
		this.discountValue = discountValue;
	}
}
