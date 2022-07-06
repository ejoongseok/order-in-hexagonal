package kata.orderinhexagonal.payment.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;
import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import kata.orderinhexagonal.payment.application.port.out.OrderLoadPort;
import kata.orderinhexagonal.payment.application.port.out.PaymentSavePort;
import kata.orderinhexagonal.payment.application.port.out.RequestPay;
import kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import kata.orderinhexagonal.payment.domain.Payment;
import kata.orderinhexagonal.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUsecase {
	private final OrderLoadPort orderLoadPort;
	private final RequestPayPort requestPayPort;

	private final PaymentSavePort paymentSavePort;

	@Override
	public Payment payments(PaymentRequest request) {
		Long orderId = request.getOrderId();
		Order order = orderLoadPort.load(orderId);
		if(!OrderStatus.NOT_PAYED.equals(order.getStatus())) {
			throw new IllegalStateException("주문 상태가 잘못되었습니다.");
		}
		RequestPay requestPay = RequestPay.of(request.getCardType(), request.getCardCompany(), request.getCardNumber(),
			request.getCardCvc(),
			order.getTotalPrice(), request.getPaymentType());
		PaymentStatus paymentStatus = requestPayPort.pay(requestPay);
		if (PaymentStatus.OK.equals(paymentStatus)) {
			order.payed();
		}
		Payment payment = new Payment(order, request.getPaymentType(), request.getCardType(), request.getCardCompany(),
			request.getCardNumber(), request.getCardCvc(), paymentStatus);
		paymentSavePort.save(payment);

		return payment;
	}
}
