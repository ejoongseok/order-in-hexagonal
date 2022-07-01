package kata.orderinhexagonal.member.domain;

import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
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

	public Member(long id, String name, String email, String location) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.location = location;
	}

	public static Member toDomain(MemberEntity memberEntity) {
		return new Member(memberEntity.getId(), memberEntity.getName(), memberEntity.getEmail(), memberEntity.getLocation());
	}

	public void assignId(long nextId) {
		this.id = nextId;
	}
}

