package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoadOrdererAdapter implements LoadOrdererPort {
	private final ItemOrdererNetworkClient itemOrdererNetworkClient;

	@Override
	public Member load(long id) {
		return itemOrdererNetworkClient.loadOrderer(id);
	}
}
