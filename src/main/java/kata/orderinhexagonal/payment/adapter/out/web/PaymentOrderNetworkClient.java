package kata.orderinhexagonal.payment.adapter.out.web;

import kata.orderinhexagonal.order.domain.Order;

public interface PaymentOrderNetworkClient {
	Order findOrder(Long orderId);
}
