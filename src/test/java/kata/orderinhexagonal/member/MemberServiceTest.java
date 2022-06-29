package kata.orderinhexagonal.member;

import static org.mockito.Mockito.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import kata.orderinhexagonal.member.application.service.MemberService;
import kata.orderinhexagonal.member.domain.Member;

@SpringBootTest
class MemberServiceTest {

	@SpyBean
	MemberJoinValidator joinValidator;
	@SpyBean PasswordEncoder passwordEncoder;
	@SpyBean SaveMemberPort saveMemberPort;

	@Autowired MemberService memberService;

	@Test
	void join() {
		// given
		String email = "ejoongseok@gmail.com";
		String password = "ejoongseok1234!";
		String name = "이중석";
		String location = "대전광역시 서구";
		CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);

		// when
		Member joinMember = memberService.join(request);

		// then
		Assertions.assertThat(joinMember.getId()).isPositive();
		verify(joinValidator, times(1)).verifyExistsEmail(joinMember.getEmail());
		verify(passwordEncoder, times(1)).encode(password);
		verify(saveMemberPort, times(1)).save(joinMember);
	}

}
