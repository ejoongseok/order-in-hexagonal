package kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.order.application.port.out.OrderStatusChangePort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderStatusChangeAdapter implements OrderStatusChangePort {

	private final OrderRepository orderRepository;

	@Override
	@Transactional
	public void statusChange(Order order) {
		OrderEntity entity = orderRepository.findById(order.getId())
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		entity.changeStatus(order.getStatus());

	}

}
