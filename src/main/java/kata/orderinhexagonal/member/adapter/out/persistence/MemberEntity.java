package kata.orderinhexagonal.member.adapter.out.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String email;
	private String password;
	private String location;

	public MemberEntity(String name, String email, String password, String location) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.location = location;
	}
}
