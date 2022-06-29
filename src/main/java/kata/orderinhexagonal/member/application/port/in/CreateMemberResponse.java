package kata.orderinhexagonal.member.application.port.in;

public class CreateMemberResponse {
	private long id;
	private String name;
	private String email;
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

	public String getLocation() {
		return location;
	}
}
