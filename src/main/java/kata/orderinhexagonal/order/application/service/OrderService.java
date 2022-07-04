package kata.orderinhexagonal.order.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUsecase {
	private final LoadOrdererPort loadOrdererPort;
	private final LoadOrderItemPort loadOrderItemPort;

	@Override
	public Order createOrder(CreateOrderRequest request) {
		Member orderer = loadOrdererPort.load(request.getOrdererId());
		Order order = new Order(orderer);
		request.getOrderItemRequests().forEach(orderItemRequest -> {
			Item item = loadOrderItemPort.loadItem(orderItemRequest.getItemId());
			item.stockOutQuantity(orderItemRequest.getOrderQuantity());
			int orderPrice = item.getPrice() * orderItemRequest.getOrderQuantity();
			order.addOrderItem(item, orderItemRequest.getOrderQuantity(), orderPrice);
		});


		// 재고 차감
		// itemOrderStockOutPort.stockOut(order.getOrderItems());
		return null;
	}
}
