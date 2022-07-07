package kata.orderinhexagonal.delivery.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.delivery.application.port.out.OrderDeliveryLoadPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderDeliveryLoadAdapter implements OrderDeliveryLoadPort {

	private final OrderDeliveryNetworkClient orderDeliveryNetworkClient;

	@Override
	public Order load(Long orderId) {
		return orderDeliveryNetworkClient.loadOrder(orderId);
	}
}
