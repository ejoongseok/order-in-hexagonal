package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.application.service.MemberService;
import kata.orderinhexagonal.member.domain.Member;

@SpringBootTest
class CreateMemberUsecaseTest {

	@Autowired CreateMemberUsecase memberService;

	@Test
	void join() {
		// given
		String email = "ejoongseok@gmail.com";
		String password = "ejoongseok1234!";
		String name = "이중석";
		String location = "대전광역시 서구";
		CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);

		// when
		Member createMember = memberService.join(request);
		// then
		Assertions.assertThat(createMember.getId()).isPositive();
		Assertions.assertThat(createMember.getName()).isEqualTo(name);
		Assertions.assertThat(createMember.getEmail()).isEqualTo(email);
		Assertions.assertThat(createMember.getPassword()).isNotEqualTo(password);
		Assertions.assertThat(createMember.getLocation()).isEqualTo(location);
	}

}
