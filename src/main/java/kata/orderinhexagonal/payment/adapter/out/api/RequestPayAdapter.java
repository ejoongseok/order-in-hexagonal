package kata.orderinhexagonal.payment.adapter.out.api;

import java.util.Map;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.payment.application.port.out.RequestPay;
import kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.PaymentStatus;
import kata.orderinhexagonal.payment.schedule.RefundRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestPayAdapter implements RequestPayPort {
	private final Map<CardCompany, PayClient> payClientsMap;
	@Override
	public PaymentStatus pay(RequestPay requestPay) {
		try {
			PayClient payClient = this.payClientsMap.get(requestPay.getCardCompany());
			payClient.process(requestPay.getCardType(), requestPay.getPaymentType(), requestPay.getCardNumber(),
				requestPay.getCardCvc(), requestPay.getPaymentPrice());
			return PaymentStatus.OK;
		} catch (Exception e) {
			return PaymentStatus.FAILED;
		}
	}

	@Override
	public void refund(RefundRequest request) {
		PayClient payClient = this.payClientsMap.get(request.getCardCompany());

		payClient.refund(request.getCardType(), request.getPaymentType(), request.getCardNumber(),
			request.getCardCvc(), request.getPaymentPrice());
	}

}
