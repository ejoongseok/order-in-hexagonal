package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.order.domain.Order;

public interface LoadOrderPort {
	Order loadOrder(Long id);
}
