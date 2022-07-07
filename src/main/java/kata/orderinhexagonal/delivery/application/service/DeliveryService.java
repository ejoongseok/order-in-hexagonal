package kata.orderinhexagonal.delivery.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.delivery.application.port.in.CreateDeliveryUsecase;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import kata.orderinhexagonal.delivery.domain.Delivery;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService implements CreateDeliveryUsecase {
	@Override
	public Delivery create(DeliveryRequest request) {
		return null;
	}
}
