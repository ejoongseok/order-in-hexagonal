package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;

public interface OrderItemNetworkClient {
	Item getOrderItem(Long id);
}
