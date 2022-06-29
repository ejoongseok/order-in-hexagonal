package kata.orderinhexagonal.member.application.service;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

public class MemberService implements CreateMemberUsecase {
	@Override
	public Member join(CreateMemberRequest request) {
		joinValidator.verifyExistsEmail(request.getEmail());
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		Member member = new Member(request.getEmail(), encodedPassword, request.getName(), request.getLocation());
		saveMemberPort.save(member);
		return member;
	}
}
