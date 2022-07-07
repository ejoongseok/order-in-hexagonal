package kata.orderinhexagonal.delivery.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.delivery.adapter.out.persistence.CreateDeliveryAdapter;
import kata.orderinhexagonal.delivery.application.port.in.CreateDeliveryUsecase;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import kata.orderinhexagonal.delivery.application.port.out.OrderDeliveryLoadPort;
import kata.orderinhexagonal.delivery.application.port.out.SaveDeliveryPort;
import kata.orderinhexagonal.delivery.domain.Delivery;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeliveryService implements CreateDeliveryUsecase {
	private final OrderDeliveryLoadPort orderDeliveryPort;
	private final SaveDeliveryPort saveDeliveryPort;

	@Override
	public Delivery create(DeliveryRequest request) {
		Order order = orderDeliveryPort.load(request.getOrderId());
		order.updateDeliveredStatus();
		Delivery delivery = new Delivery(order, request.getDeliveryStatus(), request.getLocation());
		saveDeliveryPort.save(delivery);
		return delivery;
	}
}
