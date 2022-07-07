package kata.orderinhexagonal.delivery.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface OrderDeliveryLoadPort {
	Order load(Long orderId);
}
