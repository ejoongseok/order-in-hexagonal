package kata.orderinhexagonal.member.adapter.out.persistence;

import javax.persistence.Column;
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
	@Column(name = "member_id")
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String location;

	public MemberEntity(long id, String name, String email, String password, String location) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.location = location;
	}
}
