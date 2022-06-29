package kata.orderinhexagonal.member.application.port.in;

public class CreateMemberRequest {
	private String email;
	private String password;
	private String name;
	private String location;

	public CreateMemberRequest(String email, String password, String name, String location) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.location = location;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}
}
