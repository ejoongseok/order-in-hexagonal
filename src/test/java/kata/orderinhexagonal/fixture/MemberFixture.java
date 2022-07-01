package kata.orderinhexagonal.fixture;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

public class MemberFixture {
	CreateMemberUsecase createMemberUsecase;

	public Member createMember(String name, String email, String location) {
		CreateMemberRequest request = new CreateMemberRequest(name, "ejoongseok1234!", email, location);
		Member member = createMemberUsecase.join(request);
		return member;
	}
}
