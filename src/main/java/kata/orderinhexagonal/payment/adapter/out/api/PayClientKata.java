package kata.orderinhexagonal.payment.adapter.out.api;

import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

public class PayClientKata implements PayClient {
	@Override
	public void process(CardType cardType, PaymentType paymentType, String cardNumber, String cardCvc,
		int paymentPrice) {
		System.out.println("helloKataPayWorld!");
	}
}
