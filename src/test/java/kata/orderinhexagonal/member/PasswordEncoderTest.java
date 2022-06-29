package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.member.adapter.out.persistence.CreateMemberAdapter;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;

@SpringBootTest
class PasswordEncoderTest {

	@Autowired PasswordEncoder passwordEncoder;

	@Test
	void 비밀번호_인코딩() {
		// given
		String password = "ejoongseok1234!";
		// when
		String encodedPassword = passwordEncoder.encode(password);
		// then
		Assertions.assertThat(password).isNotEqualTo(encodedPassword);
	}
}
