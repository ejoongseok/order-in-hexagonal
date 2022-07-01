package kata.orderinhexagonal.member.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.auth.JwtProvider;
import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberResponse;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

	private final CreateMemberUsecase memberUsecase;
	private final JwtProvider jwtProvider;

	@PostMapping
	public ResponseEntity<CreateMemberResponse> createMember(@RequestBody @Valid CreateMemberRequest request,Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}

		Member member = memberUsecase.join(request);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "Bearer " + jwtProvider.createJwtToken(member.getId()));
		return ResponseEntity.status(HttpStatus.CREATED)
			.headers(httpHeaders)
			.body(new CreateMemberResponse(member));
	}
}
