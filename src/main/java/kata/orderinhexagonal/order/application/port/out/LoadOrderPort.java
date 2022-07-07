package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;

public interface LoadOrderPort {
	Order loadOrder(Long orderId);
	OrderEntity loadOrderEntity (Long orderId);
}
