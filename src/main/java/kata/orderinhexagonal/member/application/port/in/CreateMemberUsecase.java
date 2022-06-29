package kata.orderinhexagonal.member.application.port.in;

import kata.orderinhexagonal.member.domain.Member;

public interface CreateMemberUsecase {

	Member join(CreateMemberRequest request);
}
