package kata.orderinhexagonal.payment.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import kata.orderinhexagonal.payment.adapter.out.api.PayClient;
import kata.orderinhexagonal.payment.adapter.out.api.PayClientKata;
import kata.orderinhexagonal.payment.domain.CardCompany;

@Configuration
public class PaymentAppConfig {

	@Bean
	public Map<CardCompany, PayClient> payClientsMap() {
		Map<CardCompany, PayClient> payClientsMap = new HashMap<>();
		payClientsMap.put(CardCompany.KATA, payClientKata());
		return payClientsMap;
	}

	@Bean
	public PayClient payClientKata() {
		return new PayClientKata();
	}
}
