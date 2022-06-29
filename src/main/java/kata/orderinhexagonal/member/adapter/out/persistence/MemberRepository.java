package kata.orderinhexagonal.member.adapter.out.persistence;

import kata.orderinhexagonal.member.domain.Member;

public interface MemberRepository {
	boolean existsByEmail(String email);

	void save(Member member);
}
