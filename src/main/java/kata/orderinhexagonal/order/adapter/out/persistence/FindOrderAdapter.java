package kata.orderinhexagonal.order.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindOrderAdapter implements LoadOrderPort {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderMapper orderMapper;

	@Override
	public Order loadOrder(Long orderId) {
		OrderEntity orderEntity = orderRepository.findOrderWithMemberById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		List<OrderItemEntity> orderItems = orderItemRepository.findByOrderId(orderEntity.getId());
		for (OrderItemEntity orderItemEntity : orderItems) {
			orderEntity.addOrderItem(orderItemEntity);
		}
		return orderMapper.toDomain(orderEntity);
	}
}
