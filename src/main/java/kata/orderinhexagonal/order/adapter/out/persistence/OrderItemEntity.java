package kata.orderinhexagonal.order.adapter.out.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity {

	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private ItemEntity item;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;

	@Column(unique = true, nullable = false)
	private Integer orderPrice;

	@Column(unique = true, nullable = false)
	private Integer orderQuantity;

	public OrderItemEntity(int orderQuantity, int orderPrice, ItemEntity itemEntity) {
		this.orderQuantity = orderQuantity;
		this.orderPrice = orderPrice;
		this.item = itemEntity;
	}

	public void setOrder(OrderEntity orderEntity) {
		this.order = orderEntity;
	}
}
