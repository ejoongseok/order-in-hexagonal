package kata.orderinhexagonal.member.application.port.out;

import kata.orderinhexagonal.member.domain.Member;

public interface SaveMemberPort {
	void save(Member member);
}
