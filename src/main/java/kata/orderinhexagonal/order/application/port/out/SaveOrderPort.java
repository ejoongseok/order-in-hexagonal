package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface SaveOrderPort {
	void save(Order order);
}
