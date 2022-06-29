package kata.orderinhexagonal.member.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.domain.Member;

@Component
public class MemberMapper {

	public MemberEntity toEntity(Member domain) {
		return new MemberEntity(domain.getName(), domain.getEmail(), domain.getPassword(), domain.getLocation());
	}
}