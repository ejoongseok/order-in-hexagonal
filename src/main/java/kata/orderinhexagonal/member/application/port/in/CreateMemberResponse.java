package kata.orderinhexagonal.member.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateMemberResponse {
	private long id;
	private String name;
	private String email;
	private String location;

	public CreateMemberResponse(String email, String name, String location) {
		this.email = email;
		this.name = name;
		this.location = location;
	}
}
