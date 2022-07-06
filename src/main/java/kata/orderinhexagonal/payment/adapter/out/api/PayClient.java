package kata.orderinhexagonal.payment.adapter.out.api;

import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

public interface PayClient {
	void process(CardType cardType, PaymentType paymentType, String cardNumber, String cardCvc, int paymentPrice);

	void refund(CardType cardType, PaymentType paymentType, String cardNumber, String cardCvc, Integer paymentPrice);
}
