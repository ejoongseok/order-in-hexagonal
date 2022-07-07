package kata.orderinhexagonal.delivery.application.port.in;

import kata.orderinhexagonal.delivery.domain.Delivery;

public interface CreateDeliveryUsecase {
	Delivery create(DeliveryRequest request);
}
