package kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberMapper;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderMapper {

	private final MemberMapper memberMapper;
	private final ItemMapper itemMapper;

	public OrderEntity toEntity(Order order) {
		OrderEntity orderEntity = new OrderEntity(order.getId(), order.getStatus());
		MemberEntity memberEntity = memberMapper.toEntity(order.getMember());
		orderEntity.initMember(memberEntity);
		for (OrderItem orderItem : order.getOrderItems()) {
			ItemEntity itemEntity = itemMapper.toEntity(orderItem.getItem());

			OrderItemEntity orderItemEntity = new OrderItemEntity(orderItem.getOrderQuantity(),
				orderItem.getOrderPrice(), itemEntity);
			orderEntity.addOrderItem(orderItemEntity);
		}
		return orderEntity;
	}
}
