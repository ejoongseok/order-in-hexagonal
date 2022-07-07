package kata.orderinhexagonal.delivery.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderDeliveryNetworkMonolithicClient implements OrderDeliveryNetworkClient {

	private final FindOrderAdapter findOrderAdapter;

	@Override
	public Order loadOrder(Long orderId) {
		return findOrderAdapter.loadOrder(orderId);
	}
}
