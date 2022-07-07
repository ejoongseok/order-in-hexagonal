package kata.orderinhexagonal.delivery.adapter.out.persistence;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDeliveryEntity {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private OrderEntity order;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "delivery_id")
	private DeliveryEntity delivery;

	public OrderDeliveryEntity(OrderEntity orderEntity, DeliveryEntity deliveryEntity) {
		this.order = orderEntity;
		this.delivery = deliveryEntity;
	}
}
