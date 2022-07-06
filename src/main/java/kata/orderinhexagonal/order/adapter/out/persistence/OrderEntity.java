package kata.orderinhexagonal.order.adapter.out.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import kata.orderinhexagonal.order.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {

	@Id
	@GeneratedValue
	@Column(name = "order_id")
	private Long id;
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItemEntity> orderItems = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private MemberEntity member;

	public OrderEntity(Long id, OrderStatus status) {
		this.id = id;
		this.status = status;
	}

	public void initMember(MemberEntity memberEntity) {
		this.member = memberEntity;
	}

	public void addOrderItem(OrderItemEntity orderItemEntity) {
		orderItemEntity.setOrder(this);
		this.orderItems.add(orderItemEntity);
	}

	public void cancel() {
		this.status = OrderStatus.CANCELED;
	}
}
