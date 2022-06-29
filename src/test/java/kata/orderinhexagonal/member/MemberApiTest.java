package kata.orderinhexagonal.member;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;

class MemberApiTest {

	void 회원가입() {

		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		Assertions.assertThat(createMemberResponse.getId()).isPositive();
		Assertions.assertThat(createMemberResponse.getName()).isEqualTo(name);
		Assertions.assertThat(createMemberResponse.getEmail()).isEqualTo(email);
		Assertions.assertThat(createMemberResponse.getLocation).isEqualTo(location);
	}
}
