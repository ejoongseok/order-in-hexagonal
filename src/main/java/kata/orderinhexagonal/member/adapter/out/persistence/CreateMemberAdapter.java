package kata.orderinhexagonal.member.adapter.out.persistence;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateMemberAdapter implements PasswordEncoder, MemberJoinValidator, SaveMemberPort {

	private final MemberRepository memberRepository;

	@Override
	public String encode(String password) {
		return new DigestUtils("SHA3-256").digestAsHex(password);
	}

	@Override
	public boolean verifyExistsEmail(String email) {
		if(memberRepository.existsByEmail(email)) {
			throw new ExistsEmailException("이미 존재하는 이메일입니다.");
		}
		return false;
	}

	@Override
	public void save(Member member) {
		memberRepository.save(member);
	}
}
