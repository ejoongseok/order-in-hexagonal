package kata.orderinhexagonal.member.adapter.out.persistence;

public interface MemberRepository {
	boolean existsByEmail(String email);
}
