package kata.orderinhexagonal.payment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.fixture.OrderFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Autowired OrderFixture orderFixture;

	@Autowired MemberFixture memberFixture;

	@BeforeEach
	void setUp() {
		// paymentRepository.deleteAll();
	    orderFixture.clearOrder();
		memberFixture.clearMember();
	}
	@AfterEach
	void tearDown() {
		// paymentRepository.deleteAll();
	    orderFixture.clearOrder();
		memberFixture.clearMember();
	}



	@Test
	void 주문_결제() throws Exception {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		String cardNumber = "1234567890123456";
		int cardCvc = 123;
		PaymentType paymentType = PaymentType.PAY_IN_FULL;
		CardType cardType = CardType.CREDIT_CARD;
		CardCompany cardCompany = CardCompany.KATA;
		PaymentRequest request = PaymentRequest.of(order.getId(), order.getTotalPrice(), cardType, cardCompany, cardNumber, cardCvc, paymentType);
		// when
		MockHttpServletResponse response = mockMvc.perform(post("/payments")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();

		// then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		PaymentResponse paymentResponse = objectMapper.readValue(response.getContentAsString(), PaymentResponse.class);
		Assertions.assertThat(paymentResponse.getId()).isPositive();
		Assertions.assertThat(paymentResponse.getOrderId()).isEqualTo(order.getId());
		OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());
		Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.PAYED);
		Order getOrder = orderFixture.getOrder(order.getId());
		Assertions.assertThat(paymentResponse.getPaymentPrice()).isEqualTo(getOrder.getTotalPrice());
		Assertions.assertThat(paymentResponse.getPaymentType()).isEqualTo(PaymentType.PAY_IN_FULL);
		Assertions.assertThat(paymentResponse.getCardType()).isEqualTo(CardType.CREDIT_CARD);
	}

	private class PaymentResponse {
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

	private enum PaymentType {
		PAY_IN_FULL
	}

	private enum CardType {
		CREDIT_CARD
	}

	private enum CardCompany {
		KATA

	}

	private static class PaymentRequest {

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
}
