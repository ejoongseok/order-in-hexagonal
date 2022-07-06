package kata.orderinhexagonal.order.application.service;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.domain.Member;

@Component
public class CancelOrderValidator {
	public boolean isOrdererAndTheRequestorMatch(Member cancelRequestor, Member orderer) {
		return cancelRequestor.equals(orderer);
	}
}
