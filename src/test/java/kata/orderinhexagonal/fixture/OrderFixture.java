package kata.orderinhexagonal.fixture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderMapper;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderRepository;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import kata.orderinhexagonal.order.domain.Order;

@Component
public class OrderFixture {

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	CreateOrderUsecase createOrderUsecase;

	@Autowired
	ItemFixture itemFixture;

	@Autowired
	StockFixture stockFixture;

	@Autowired OrderMapper orderMapper;

	public OrderEntity getOrderEntity(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
	}

	public Order getOrder(Long id) {
		OrderEntity entity = orderRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		return orderMapper.toDomain(entity);
	}

	public void clearOrder() {
		orderRepository.deleteAll();
	}

	public Order createOrder(long ordererId) {
		Item item = itemFixture.createItem("노트북", 1_000_000);
		stockFixture.stockIn(item, 10);
		CreateOrderRequest request = CreateOrderRequest.of(List.of(
			OrderItemRequest.of(item.getId(), 1)
		));
		request.assignOrdererId(ordererId);
		return createOrderUsecase.createOrder(request);
	}
}
