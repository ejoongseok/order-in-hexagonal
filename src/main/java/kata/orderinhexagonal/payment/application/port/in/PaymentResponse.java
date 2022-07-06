package kata.orderinhexagonal.payment.application.port.in;

import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

public class PaymentResponse {
	private Long id;
	private Long orderId;
	private int paymentPrice;
	private PaymentType paymentType;
	private CardType cardType;

	public Long getId() {
		return id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public int getPaymentPrice() {
		return paymentPrice;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public CardType getCardType() {
		return cardType;
	}
}
