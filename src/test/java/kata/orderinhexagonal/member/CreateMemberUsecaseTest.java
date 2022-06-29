package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;

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

	private static class Member {
		private long id;
		private String name;
		private String email;
		private String password;
		private String location;

		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		public String getLocation() {
			return location;
		}
	}
}
