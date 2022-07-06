package kata.orderinhexagonal.payment.schedule;

import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.Getter;

@Getter
public class RefundRequest {
	private final CardType cardType;
	private final CardCompany cardCompany;
	private final PaymentType paymentType;
	private final String cardNumber;
	private final String cardCvc;
	private final Integer paymentPrice;

	public RefundRequest(CardType cardType, CardCompany cardCompany, PaymentType paymentType, String cardNumber,
		String cardCvc, Integer paymentPrice) {

		this.cardType = cardType;
		this.cardCompany = cardCompany;
		this.paymentType = paymentType;
		this.cardNumber = cardNumber;
		this.cardCvc = cardCvc;
		this.paymentPrice = paymentPrice;
	}
}
