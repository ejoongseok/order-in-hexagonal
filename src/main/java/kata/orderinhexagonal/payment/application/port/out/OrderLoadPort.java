package kata.orderinhexagonal.payment.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface OrderLoadPort {
	Order load(Long orderId);
}
