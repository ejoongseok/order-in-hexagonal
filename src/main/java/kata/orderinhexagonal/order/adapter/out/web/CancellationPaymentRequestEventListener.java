package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CancellationPaymentRequestEventListener {
	private final OrderPaymentNetworkClient orderPaymentNetworkClient;

	@Async
	@EventListener
	public void cancellation(CancellationPaymentRequestEvent event) {
		orderPaymentNetworkClient.cancelPaymentRequest(event.getOrderId());
	}
}
