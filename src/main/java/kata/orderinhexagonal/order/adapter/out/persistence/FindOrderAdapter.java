package kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindOrderAdapter implements LoadOrderPort {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;

	@Override
	public Order loadOrder(Long orderId) {
		return null;
	}
}
