package kata.orderinhexagonal.delivery.application.port.out;

import kata.orderinhexagonal.delivery.domain.Delivery;

public interface SaveDeliveryPort {
	void save(Delivery delivery);
}
