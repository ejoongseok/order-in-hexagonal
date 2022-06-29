package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.adapter.out.persistence.CreateMemberAdapter;
import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;

class MemberJoinValidatorTest {

	private final MemberJoinValidator memberJoinValidator = new CreateMemberAdapter();

	@Test
	void 이메일_존재여부_확인() {
		//given
		String email = "ejoongseok@gmail.com";
		//when
		boolean verify = memberJoinValidator.verifyExistsEmail(email);
		//then
		Assertions.assertThat(verify).isFalse();
	}
}
