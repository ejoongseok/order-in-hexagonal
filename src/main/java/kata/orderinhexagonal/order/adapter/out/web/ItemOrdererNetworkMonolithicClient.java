package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberMapper;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberSpringDataJpaRepository;
import kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ItemOrdererNetworkMonolithicClient implements ItemOrdererNetworkClient{
	private final MemberSpringDataJpaRepository memberRepository;
	private final MemberMapper memberMapper;

	@Override
	public Member loadOrderer(long id) {
		MemberEntity memberEntity = memberRepository.findById(id)
			.orElseThrow(() -> new RuntimeException("Member not found"));
		return memberMapper.toDomain(memberEntity);
	}
}
