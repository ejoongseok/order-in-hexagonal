package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

class CreateMemberUsecaseTest {

	private CreateMemberUsecase memberService = new MemberService();

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
		Assertions.assertThat(createMember.getPassword()).isEqualTo(password);
		Assertions.assertThat(createMember.getLocation()).isEqualTo(location);
	}

	private class MemberService implements CreateMemberUsecase {
		@Override
		public Member join(CreateMemberRequest request) {
			joinValidator.verifyExistsEmail(request.getEmail());
			String encodedPassword = passwordEncoder.encode(request.getPassword());
			Member member = new Member(request.getEmail(), encodedPassword, request.getName(), request.getLocation());
			saveMemberPort.save(member);
			return member;
		}
	}
}
