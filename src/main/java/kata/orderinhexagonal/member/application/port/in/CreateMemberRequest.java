package kata.orderinhexagonal.member.application.port.in;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberRequest {

	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotBlank(message = "이메일을 입력해주세요.")
	private String email;

	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+=])[A-Za-z\\d~!@#$%^&*()+=]{8,16}$",
		message = "숫자', '문자', '특수문자'를 1개 이상, 비밀번호는 '최소 8자에서 최대 16자' 안으로 입력해 주세요.")
	@NotBlank(message = "비밀번호를 입력해 주세요.")
	private String password;
	@NotBlank(message = "이름을 입력해 주세요.")
	private String name;
	@NotBlank(message = "주소를 입력해 주세요.")
	private String location;

	public CreateMemberRequest(String email, String password, String name, String location) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.location = location;
	}
}
