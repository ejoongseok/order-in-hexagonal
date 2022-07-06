package kata.orderinhexagonal.payment.application.port.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.Getter;

@Getter
public class PaymentRequest {

	@NotNull(message = "주문 번호를 입력해주세요.")
	private Long orderId;

	@Min(value = 0, message = "최소 0원 이상의 금액을 입력해주세요.")
	private int totalPrice;
	@NotNull(message = "결제 수단을 선택해주세요.")
	private CardType cardType;
	@NotNull(message = "카드사를 선택해주세요.")
	private CardCompany cardCompany;
	@Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$", message = "카드번호를 정확히 입력해주세요.")
	@NotNull(message = "카드번호를 입력해주세요.")
	private String cardNumber;
	@Pattern(regexp = "^\\d{3}$", message = "카드 뒷면의 cvc 번호를 정확히 입력해주세요.")
	@NotNull(message = "카드 뒷면의 cvc 번호를 입력해주세요.")
	private int cardCvc;
	@NotNull(message = "할부 여부를 입력해주세요.")
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
}
