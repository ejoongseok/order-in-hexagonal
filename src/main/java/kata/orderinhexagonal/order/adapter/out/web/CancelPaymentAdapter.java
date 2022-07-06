package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.order.application.port.out.CancelPaymentPort;
import kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancelPaymentAdapter implements CancelPaymentPort {

	private final ApplicationEventPublisher eventPublisher;


	@Override
	public void request(Order order) {
		eventPublisher.publishEvent(new CancellationPaymentRequestEvent(order.getId()));

	}
}
