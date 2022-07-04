package kata.orderinhexagonal.fixture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderItemEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderItemRepository;

@Component
public class OrderItemFixture {

	@Autowired
	OrderItemRepository orderItemRepository;

	public List<OrderItemEntity> getOrderItems(Long id) {
		return orderItemRepository.findByOrderId(id);
	}
}
