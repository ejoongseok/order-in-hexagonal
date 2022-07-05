package kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
import kata.orderinhexagonal.order.application.port.out.SaveOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersistenceOrderAdapter implements SaveOrderPort, LoadOrderPort {

	private final OrderRepository orderRepository;

	private final OrderMapper orderMapper;

	@Override
	@Transactional
	public void save(Order order) {
		OrderEntity entity = orderMapper.toEntity(order);
		orderRepository.save(entity);
		order.assignId(entity.getId());
		for (int i = 0; i < entity.getOrderItems().size(); i++) {
			OrderItemEntity orderItemEntity = entity.getOrderItems().get(i);
			OrderItem orderItem = order.getOrderItems().get(i);
			orderItem.assignId(orderItemEntity.getId());
		}
	}

	@Override
	public Order loadOrder(Long id) {
		return null;
	}
}
