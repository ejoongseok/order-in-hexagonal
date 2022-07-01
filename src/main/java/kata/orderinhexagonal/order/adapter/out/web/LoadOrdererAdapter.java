package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.member.domain.Member;
import kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;

public class LoadOrdererAdapter implements LoadOrdererPort {
	ItemOrdererNetworkClient itemOrdererNetworkClient;

	@Override
	public Member load(long id) {
		return itemOrdererNetworkClient.loadOrderer(id);
	}
}
