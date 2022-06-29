package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.member.adapter.out.persistence.CreateMemberAdapter;
import kata.orderinhexagonal.member.adapter.out.persistence.ExistsEmailException;
import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import kata.orderinhexagonal.member.domain.Member;

class MemberJoinValidatorTest {

	private final MemberJoinValidator memberJoinValidator = new CreateMemberAdapter();

	@Test
	@DisplayName("존재하지 않는 이메일")
	void 이메일_존재여부_확인() {
		//given
		String email = "ejoongseok@gmail.com";
		//when
		boolean verify = memberJoinValidator.verifyExistsEmail(email);
		//then
		Assertions.assertThat(verify).isFalse();
	}

	@Test
	@DisplayName("존재하는 이메일")
	void 이메일_존재여부_확인_이미존재() {
		//given
		CreateMemberAdapter createMemberAdapter = new CreateMemberAdapter();
		MemberJoinValidator memberJoinValidator = createMemberAdapter;
		SaveMemberPort saveMemberPort = createMemberAdapter;
		String email = "ejoongseok@gmail.com";
		saveMemberPort.save(new Member(email, "ejoongseok1234!", "이중석", "대전광역시 서구"));
		//when
		Assertions.assertThatThrownBy(() -> memberJoinValidator.verifyExistsEmail(email))
			.isInstanceOf(ExistsEmailException.class);
	}
}
