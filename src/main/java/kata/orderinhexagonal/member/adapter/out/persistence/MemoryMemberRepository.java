package kata.orderinhexagonal.member.adapter.out.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import kata.orderinhexagonal.member.domain.Member;

public class MemoryMemberRepository	implements MemberRepository {

	private final Map<Long, Member> memoryPersistence = new ConcurrentHashMap<>();

	@Override
	public boolean existsByEmail(String email) {
		return memoryPersistence.values().stream().anyMatch(member -> member.getEmail().equals(email));
	}
}
