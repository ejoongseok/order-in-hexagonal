package kata.orderinhexagonal.payment.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentOrderNetworkMonolithicClient implements PaymentOrderNetworkClient {

	private final FindOrderAdapter findOrderAdapter;
	@Override
	public Order findOrder(Long orderId) {
		return findOrderAdapter.loadOrder(orderId);
	}
}
