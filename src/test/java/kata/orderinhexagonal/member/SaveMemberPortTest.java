package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.adapter.out.persistence.CreateMemberAdapter;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import kata.orderinhexagonal.member.domain.Member;

class SaveMemberPortTest {
	private SaveMemberPort saveMemberPort;

	@Test
	void member_save() {
		// given
		PasswordEncoder passwordEncoder = new CreateMemberAdapter();
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
