package kata.orderinhexagonal.order.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindOrderAdapter implements LoadOrderPort {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final OrderMapper orderMapper;
	private final ItemMapper itemMapper;

	@Override
	public Order loadOrder(Long orderId) {
		OrderEntity orderEntity = orderRepository.findOrderWithMemberById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		Order order = orderMapper.toDomain(orderEntity);
		List<OrderItemEntity> orderItems = orderItemRepository.findByOrderId(orderEntity.getId());
		for (OrderItemEntity orderItemEntity : orderItems) {
			order.addOrderItem(itemMapper.toDomain(orderItemEntity.getItem()), orderItemEntity.getOrderQuantity(), orderItemEntity.getOrderPrice());
		}

		return order;
	}

	@Override
	public OrderEntity loadOrderEntity(Long orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
	}
}
