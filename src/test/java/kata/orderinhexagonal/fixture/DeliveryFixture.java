package kata.orderinhexagonal.fixture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.delivery.adapter.out.persistence.DeliveryEntity;
import kata.orderinhexagonal.delivery.adapter.out.persistence.DeliveryRepository;
import kata.orderinhexagonal.delivery.adapter.out.persistence.OrderDeliveryEntity;
import kata.orderinhexagonal.delivery.adapter.out.persistence.OrderDeliveryRepository;

@Component
public class DeliveryFixture {

	@Autowired
	DeliveryRepository deliveryRepository;

	@Autowired
	OrderDeliveryRepository orderDeliveryRepository;

	public DeliveryEntity getDeliveryEntity(Long deliveryId) {
		return deliveryRepository.findById(deliveryId).get();
	}

	public List<OrderDeliveryEntity> getOrderDeliveryList(Long orderId) {
		return orderDeliveryRepository.findByOrderId(orderId);
	}

	public void clearDelivery() {
		orderDeliveryRepository.deleteAll();
		deliveryRepository.deleteAll();
	}
}
