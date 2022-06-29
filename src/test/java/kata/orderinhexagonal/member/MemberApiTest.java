package kata.orderinhexagonal.member;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.assertj.core.api.Assertions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

class MemberApiTest {

	ObjectMapper objectMapper;
	MockMvc mockMvc;

	void 회원가입() throws Exception {
		//given
		String email = "ejoongseok@gmail.com";
		String password = "ejoongseok1234!";
		String name = "이중석";
		String location = "대전광역시 서구";
		CreateMemberRequest request = new CreateMemberRequest(email, password, name, location);

		//when
		MockHttpServletResponse response = mockMvc.perform(post("/members")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request))
		).andReturn().getResponse();
		//then
		Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
		CreateMemberResponse createMemberResponse = objectMapper.readValue(response.getContentAsString(), CreateMemberResponse.class);
		Assertions.assertThat(createMemberResponse.getId()).isPositive();
		Assertions.assertThat(createMemberResponse.getName()).isEqualTo(name);
		Assertions.assertThat(createMemberResponse.getEmail()).isEqualTo(email);
		Assertions.assertThat(createMemberResponse.getLocation()).isEqualTo(location);
	}

	private static class CreateMemberResponse {
		private long id;
		private String name;
		private String email;
		private String location;

		public long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getEmail() {
			return email;
		}

		public String getLocation() {
			return location;
		}
	}

	private static class CreateMemberRequest {
		private String email;
		private String password;
		private String name;
		private String location;

		public CreateMemberRequest(String email, String password, String name, String location) {
			this.email = email;
			this.password = password;
			this.name = name;
			this.location = location;
		}

		public String getEmail() {
			return email;
		}

		public String getPassword() {
			return password;
		}

		public String getName() {
			return name;
		}

		public String getLocation() {
			return location;
		}
	}
}
