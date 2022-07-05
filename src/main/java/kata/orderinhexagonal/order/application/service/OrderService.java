package kata.orderinhexagonal.order.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CancelOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import kata.orderinhexagonal.order.application.port.out.SaveOrderPort;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService implements CreateOrderUsecase, CancelOrderUsecase {
	private final LoadOrdererPort loadOrdererPort;
	private final LoadOrderItemPort loadOrderItemPort;
	private final ItemOrderStockOutPort itemOrderStockOutPort;

	private final SaveOrderPort saveOrderPort;

	@Override
	public Order createOrder(CreateOrderRequest request) {
		Member orderer = loadOrdererPort.load(request.getOrdererId());
		Order order = new Order(orderer);
		request.getOrderItemRequests().forEach(orderItemRequest -> {
			Item item = loadOrderItemPort.loadItem(orderItemRequest.getItemId());
			int orderPrice = order.calculatePrice(orderItemRequest.getOrderQuantity(), item.getPrice(), item.getDiscount());
			order.addOrderItem(item, orderItemRequest.getOrderQuantity(), orderPrice);
		});
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			itemOrderStockOutPort.stockOut(orderItem.getItem(), orderItem.getOrderQuantity());
		}
		saveOrderPort.save(order);
		return order;
	}

	@Override
	public Order cancelOrder(CancelOrderRequest request) {
		return null;
	}
}
