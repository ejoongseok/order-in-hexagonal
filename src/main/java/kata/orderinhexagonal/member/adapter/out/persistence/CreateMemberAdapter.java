package kata.orderinhexagonal.member.adapter.out.persistence;

import org.apache.commons.codec.digest.DigestUtils;

import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;

public class CreateMemberAdapter implements PasswordEncoder, MemberJoinValidator {

	MemberRepository memberRepository = new MemoryMemberRepository();

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

}
