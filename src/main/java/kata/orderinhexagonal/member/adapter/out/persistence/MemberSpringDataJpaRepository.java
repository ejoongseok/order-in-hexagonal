package kata.orderinhexagonal.member.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import kata.orderinhexagonal.member.domain.Member;

public interface MemberSpringDataJpaRepository extends JpaRepository<MemberEntity, Long> {

	boolean existsByEmail(String email);
}
