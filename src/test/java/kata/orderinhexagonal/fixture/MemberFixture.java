package kata.orderinhexagonal.fixture;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import kata.orderinhexagonal.member.adapter.out.persistence.MemberSpringDataJpaRepository;
import kata.orderinhexagonal.member.application.port.in.CreateMemberRequest;
import kata.orderinhexagonal.member.application.port.in.CreateMemberUsecase;
import kata.orderinhexagonal.member.domain.Member;

@Component
public class MemberFixture {
	@Autowired CreateMemberUsecase createMemberUsecase;

	@Autowired
	MemberSpringDataJpaRepository repository;

	public void clearMember() {
		repository.deleteAll();
	}
	public Member createMember(String name, String email, String location) {
		CreateMemberRequest request = new CreateMemberRequest(name, "ejoongseok1234!", email, location);
		Member member = createMemberUsecase.join(request);
		return member;
	}

	public MockHttpServletResponse getAccessToken(String name, String email, String location, MockMvc mockMvc, ObjectMapper objectMapper) throws
		Exception {
		String password = "ejoongseok1234!";
		CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);
		return mockMvc.perform(post("/members")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();
	}

	public MemberEntity getMember(long id) {
		return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
	}
}
