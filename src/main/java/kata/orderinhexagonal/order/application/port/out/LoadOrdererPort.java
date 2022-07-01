package kata.orderinhexagonal.order.application.port.out;

import kata.orderinhexagonal.member.domain.Member;

public interface LoadOrdererPort {
	Member load(long id);
}
