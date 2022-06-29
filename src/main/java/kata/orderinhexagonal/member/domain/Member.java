package kata.orderinhexagonal.member.domain;

import lombok.Getter;

@Getter
public class Member {
	private long id;
	private String name;
	private String email;
	private String password;
	private String location;

	public Member(String email, String encodedPassword, String name, String location) {
		this.email = email;
		this.password = encodedPassword;
		this.name = name;
		this.location = location;
	}
}

