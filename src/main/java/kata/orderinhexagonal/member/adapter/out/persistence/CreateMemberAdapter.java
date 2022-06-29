package kata.orderinhexagonal.member.adapter.out.persistence;

import org.apache.commons.codec.digest.DigestUtils;

import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;

public class CreateMemberAdapter implements PasswordEncoder, MemberJoinValidator {
	@Override
	public String encode(String password) {
		return new DigestUtils("SHA3-256").digestAsHex(password);
	}

	@Override
	public boolean existsEmail(String email) {
		return false;
	}
}
