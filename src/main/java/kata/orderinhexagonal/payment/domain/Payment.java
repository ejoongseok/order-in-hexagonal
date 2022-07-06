package kata.orderinhexagonal.payment.domain;

public class Payment {
	private Long id;
	private Long orderId;
	private int paymentPrice;
	private PaymentType paymentType;
	private CardType cardType;
	private CardCompany cardCompany;
	private String cardNumber;
	private String cardCvc;
	private PaymentStatus status;

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

	public CardCompany getCardCompany() {
		return cardCompany;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardCvc() {
		return cardCvc;
	}

	public PaymentStatus getStatus() {
		return status;
	}
}
