package kata.orderinhexagonal.member.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.domain.Member;

@Component
public class MemberMapper {

	public MemberEntity toEntity(Member domain) {
		return new MemberEntity(domain.getId(), domain.getName(), domain.getEmail(), domain.getPassword(), domain.getLocation());
	}

	public Member toDomain(MemberEntity memberEntity) {
		return Member.toDomain(memberEntity);
	}
}
