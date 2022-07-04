package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderRepository;

@Component
public class OrderFixture {

	@Autowired
	OrderRepository orderRepository;

	public OrderEntity getOrderEntity(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
	}

	public void clearOrder() {
		orderRepository.deleteAll();
	}
}
