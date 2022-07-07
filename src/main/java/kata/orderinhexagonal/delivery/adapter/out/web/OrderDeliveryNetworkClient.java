package kata.orderinhexagonal.delivery.adapter.out.web;

import kata.orderinhexagonal.order.domain.Order;

public interface OrderDeliveryNetworkClient {
	Order loadOrder(Long orderId);
}
