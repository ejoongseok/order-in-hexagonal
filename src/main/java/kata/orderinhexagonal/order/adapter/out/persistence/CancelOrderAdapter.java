package kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.order.application.port.out.CancelOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancelOrderAdapter implements CancelOrderPort {
	private final OrderRepository orderRepository;
	@Override
	@Transactional
	public void cancel(Order cancelOrder) {
		cancelOrder.cancel();
		OrderEntity orderEntity = orderRepository.findById(cancelOrder.getId())
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		orderEntity.cancel();
	}
}
