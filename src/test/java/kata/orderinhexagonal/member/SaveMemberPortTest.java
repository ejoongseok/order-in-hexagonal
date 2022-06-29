package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.member.adapter.out.persistence.CreateMemberAdapter;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import kata.orderinhexagonal.member.domain.Member;

@SpringBootTest
class SaveMemberPortTest {
	@Autowired SaveMemberPort saveMemberPort;
	@Autowired PasswordEncoder passwordEncoder;

	@Test
	void member_save() {
		// given
		String email = "ejoongseok@gmail.com";
		String password = "ejoongseok1234!";
		String name = "이중석";
		String location = "대전광역시 서구";
		Member member = new Member(email, passwordEncoder.encode(password), name, location);
		// when
	    saveMemberPort.save(member);
		// then
		Assertions.assertThat(member.getId()).isPositive();
	}

}
