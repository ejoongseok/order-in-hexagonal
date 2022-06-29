package kata.orderinhexagonal.member.adapter.out.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import kata.orderinhexagonal.member.domain.Member;

@Repository
public class MemoryMemberRepository	implements MemberRepository {

	private final Map<Long, Member> memoryPersistence = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);

	@Override
	public boolean existsByEmail(String email) {
		return memoryPersistence.values().stream().anyMatch(member -> member.getEmail().equals(email));
	}

	@Override
	public void save(Member member) {
		member.assignId(nextId());
		memoryPersistence.put(member.getId(), member);
	}

	@Override
	public void deleteAll() {
		memoryPersistence.clear();
	}

	private long nextId() {
		return sequence.incrementAndGet();
	}
}
