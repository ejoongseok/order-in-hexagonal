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
import kata.orderinhexagonal.fixture.PaymentFixture;
import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.order.domain.OrderStatus;
import kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import kata.orderinhexagonal.payment.application.port.in.PaymentResponse;
import kata.orderinhexagonal.payment.domain.CardCompany;
import kata.orderinhexagonal.payment.domain.CardType;
import kata.orderinhexagonal.payment.domain.PaymentType;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentApiTest {

	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;

	@Autowired OrderFixture orderFixture;

	@Autowired MemberFixture memberFixture;

	@Autowired
	PaymentFixture paymentFixture;

	@BeforeEach
	void setUp() {
		paymentFixture.clearPayment();
	    orderFixture.clearOrder();
		memberFixture.clearMember();
	}
	@AfterEach
	void tearDown() {
		paymentFixture.clearPayment();
	    orderFixture.clearOrder();
		memberFixture.clearMember();
	}



	@Test
	void 주문_결제() throws Exception {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		Order order = orderFixture.createOrder(member.getId());
		String cardNumber = "1234567890123456";
		String cardCvc = "123";
		PaymentType paymentType = PaymentType.PAY_IN_FULL;
		CardType cardType = CardType.CREDIT_CARD;
		CardCompany cardCompany = CardCompany.KATA;
		PaymentRequest request = PaymentRequest.of(order.getId(), cardType, cardCompany, cardNumber, cardCvc, paymentType);
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
		Assertions.assertThat(paymentResponse.getPaymentType()).isEqualTo(PaymentType.PAY_IN_FULL);
		Assertions.assertThat(paymentResponse.getCardType()).isEqualTo(CardType.CREDIT_CARD);
		Assertions.assertThat(paymentResponse.getPaymentPrice()).isEqualTo(order.getTotalPrice());
	}

}
