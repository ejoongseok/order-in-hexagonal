package kata.orderinhexagonal.member.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.application.port.out.MemberJoinValidator;
import kata.orderinhexagonal.member.application.port.out.PasswordEncoder;
import kata.orderinhexagonal.member.application.port.out.SaveMemberPort;
import kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService implements CreateMemberUsecase {
	private final MemberJoinValidator joinValidator;
	private final PasswordEncoder passwordEncoder;
	private final SaveMemberPort saveMemberPort;

	@Override
	public Member join(CreateMemberRequest request) {
		joinValidator.verifyExistsEmail(request.getEmail());
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		Member member = new Member(request.getEmail(), encodedPassword, request.getName(), request.getLocation());
		saveMemberPort.save(member);
		return member;
	}
}
