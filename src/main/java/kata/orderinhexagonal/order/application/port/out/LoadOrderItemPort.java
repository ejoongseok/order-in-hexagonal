package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.item.domain.Item;

public interface LoadOrderItemPort {
	Item loadItem(Long id);
}
