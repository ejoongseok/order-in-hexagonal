package kata.orderinhexagonal.order.adapter.out.web;

import kata.orderinhexagonal.member.domain.Member;

public interface ItemOrdererNetworkClient {
	Member loadOrderer(long id);
}
