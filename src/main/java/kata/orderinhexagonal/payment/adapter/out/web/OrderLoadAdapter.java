package kata.orderinhexagonal.payment.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.payment.application.port.out.OrderLoadPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderLoadAdapter implements OrderLoadPort {

	private final PaymentOrderNetworkClient paymentOrderNetworkClient;

	@Override
	public Order load(Long orderId) {
		return paymentOrderNetworkClient.findOrder(orderId);
	}
}
