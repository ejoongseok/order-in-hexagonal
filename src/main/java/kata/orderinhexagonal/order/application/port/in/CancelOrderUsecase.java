package kata.orderinhexagonal.order.application.port.in;

import kata.orderinhexagonal.order.domain.Order;

public interface CancelOrderUsecase {
	Order cancelOrder(CancelOrderRequest request);
}
