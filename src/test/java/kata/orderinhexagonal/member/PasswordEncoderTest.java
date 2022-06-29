package kata.orderinhexagonal.member;

import org.apache.commons.codec.digest.DigestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;

class PasswordEncoderTest {

	private PasswordEncoder passwordEncoder = new CreateMemberAdapter();

	@Test
	void 비밀번호_인코딩() {
		// given
		String password = "ejoongseok1234!";
		// when
		String encodedPassword = passwordEncoder.encode(password);
		// then
		Assertions.assertThat(password).isNotEqualTo(encodedPassword);
	}

	private static class CreateMemberAdapter implements PasswordEncoder {
		@Override
		public String encode(String password) {
			return new DigestUtils("SHA3-256").digestAsHex(password);
		}
	}
}
