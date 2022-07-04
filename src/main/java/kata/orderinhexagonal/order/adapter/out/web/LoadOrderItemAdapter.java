package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadOrderItemAdapter implements LoadOrderItemPort {

	private final OrderItemNetworkClient orderItemNetworkClient;

	@Override
	public Item loadItem(Long id) {
		return orderItemNetworkClient.getOrderItem(id);
	}
}
