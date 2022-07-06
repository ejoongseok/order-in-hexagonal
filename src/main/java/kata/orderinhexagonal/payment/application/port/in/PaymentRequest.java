package kata.orderinhexagonal.payment.application.port.in;

import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

public class PaymentRequest {

	private Long orderId;
	private int totalPrice;
	private CardType cardType;
	private CardCompany cardCompany;
	private String cardNumber;
	private int cardCvc;
	private PaymentType paymentType;

	public PaymentRequest(Long orderId, int totalPrice, CardType cardType, CardCompany cardCompany,
		String cardNumber, int cardCvc, PaymentType paymentType) {
		this.orderId = orderId;
		this.totalPrice = totalPrice;
		this.cardType = cardType;
		this.cardCompany = cardCompany;
		this.cardNumber = cardNumber;
		this.cardCvc = cardCvc;
		this.paymentType = paymentType;
	}

	public static PaymentRequest of(Long orderId, int totalPrice, CardType cardType, CardCompany cardCompany,
		String cardNumber, int cardCvc, PaymentType paymentType) {
		return new PaymentRequest(orderId, totalPrice, cardType, cardCompany, cardNumber, cardCvc, paymentType);
	}

	public Long getOrderId() {
		return orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public CardType getCardType() {
		return cardType;
	}

	public CardCompany getCardCompany() {
		return cardCompany;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public int getCardCvc() {
		return cardCvc;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
}
