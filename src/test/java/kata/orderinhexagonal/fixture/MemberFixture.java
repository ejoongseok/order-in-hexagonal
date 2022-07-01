package kata.orderinhexagonal.fixture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	public String getAccessToken(String name, String email, String location, MockMvc mockMvc, ObjectMapper objectMapper) throws
		Exception {
		String password = "ejoongseok1234!";
		CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);
		return mockMvc.perform(post("/members")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse().getHeader("Authorization");
	}
}
