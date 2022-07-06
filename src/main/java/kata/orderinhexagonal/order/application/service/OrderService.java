package kata.orderinhexagonal.order.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CancelOrderUsecase;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.out.CancelOrderPort;
import kata.orderinhexagonal.order.application.port.out.CancelStockOutItemPort;
import kata.orderinhexagonal.order.application.port.out.ItemOrderStockOutPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
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
	private final LoadOrderPort loadOrderPort;
	private final CancelStockOutItemPort cancelStockOutItemPort;
	private final CancelOrderPort cancelOrderPort;

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
		Member cancelRequester = loadOrdererPort.load(request.getOrdererId());
		Order order = loadOrderPort.loadOrder(request.getOrderId());
		if (!order.isOrdererAndTheRequesterMatch(cancelRequester)) {
			throw new IllegalArgumentException("주문한 사람과 취소 요청자가 다릅니다.");
		}
		if (order.isDeliverd()) {
			throw new IllegalArgumentException("배송이 시작되어 주문을 취소 할 수 없습니다.");
		}
		if (order.isPayed()) {
			// 환불 요청
		}
		List<OrderItem> orderItems = order.getOrderItems();
		for (OrderItem orderItem : orderItems) {
			cancelStockOutItemPort.cancelStockOutItem(orderItem.getItem(), orderItem.getOrderQuantity());
		}
		cancelOrderPort.cancel(order);
		return order;
	}
}
