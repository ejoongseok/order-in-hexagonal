package kata.orderinhexagonal.delivery.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.delivery.application.port.out.SaveDeliveryPort;
import kata.orderinhexagonal.delivery.domain.Delivery;
import kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderStatusChangeAdapter;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateDeliveryAdapter implements SaveDeliveryPort {

	private final DeliveryMapper deliveryMapper;
	private final OrderStatusChangeAdapter orderStatusChangeAdapter;
	private final FindOrderAdapter findOrderAdapter;

	private final DeliveryRepository deliveryRepository;
	private final OrderDeliveryRepository orderDeliveryRepository;

	@Override
	@Transactional
	public void save(Delivery delivery) {
		DeliveryEntity deliveryEntity = deliveryMapper.toEntity(delivery);
		Order order = delivery.getOrder();
		orderStatusChangeAdapter.statusChange(order);
		OrderEntity orderEntity = findOrderAdapter.loadOrderEntity(order.getId());
		OrderDeliveryEntity orderDeliveryEntity = new OrderDeliveryEntity(orderEntity, deliveryEntity);
		deliveryRepository.save(deliveryEntity);
		orderDeliveryRepository.save(orderDeliveryEntity);
		delivery.assignId(deliveryEntity.getId());
	}
}
