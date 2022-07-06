package kata.orderinhexagonal.payment.application.port.in;

import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.Getter;

@Getter
public class PaymentResponse {
	private Long id;
	private Long orderId;
	private int paymentPrice;
	private PaymentType paymentType;
	private CardType cardType;
}
