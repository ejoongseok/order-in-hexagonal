package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

@Component
public class MemberFixture {
	@Autowired CreateMemberUsecase createMemberUsecase;

	public Member createMember(String name, String email, String location) {
		CreateMemberRequest request = new CreateMemberRequest(name, "ejoongseok1234!", email, location);
		Member member = createMemberUsecase.join(request);
		return member;
	}
}
