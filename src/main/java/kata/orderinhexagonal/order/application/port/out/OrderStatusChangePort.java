package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface OrderStatusChangePort {
	void statusChange(Order order);
}
