package kata.orderinhexagonal.member.adapter.out.persistence;

public class MemoryMemberRepository	implements MemberRepository {
	@Override
	public boolean existsByEmail(String email) {
		return false;
	}
}
