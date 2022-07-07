package kata.orderinhexagonal.delivery.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.delivery.domain.Delivery;

@Component
public class DeliveryMapper {

	public DeliveryEntity toEntity(Delivery domain) {
		return new DeliveryEntity(domain.getId(), domain.getStatus(), domain.getLocation(), domain.getCreatedDateTime());
	}
}
