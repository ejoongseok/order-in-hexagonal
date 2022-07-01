package kata.orderinhexagonal.order.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import kata.orderinhexagonal.order.domain.Order;

@Service
public class OrderService implements CreateOrderUsecase {
	private LoadOrdererPort loadOrdererPort;

	@Override
	public Order createOrder(CreateOrderRequest request) {
		Member orderer = loadOrdererPort.load(request.getOrdererId());
		return null;
	}
}
