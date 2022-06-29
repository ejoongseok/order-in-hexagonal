package kata.orderinhexagonal.member.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberResponse;

@RestController
@RequestMapping("/members")
public class MemberController {

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateMemberResponse createMember(@RequestBody @Valid CreateMemberRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}
		return new CreateMemberResponse(request.getEmail(), request.getName(),
			request.getLocation());
	}
}
