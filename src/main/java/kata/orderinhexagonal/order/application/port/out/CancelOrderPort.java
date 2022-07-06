package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface CancelOrderPort {
	void cancel(Order cancelOrder);
}
