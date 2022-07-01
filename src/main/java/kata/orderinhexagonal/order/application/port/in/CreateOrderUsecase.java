package kata.orderinhexagonal.order.application.port.in;

import kata.orderinhexagonal.order.domain.Order;

public interface CreateOrderUsecase {
	Order createOrder(CreateOrderRequest request);
}
