package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.MemberFixture;
import kata.orderinhexagonal.member.domain.Member;

@SpringBootTest
class LoadOrdererPortTest {

	@Autowired
	MemberFixture memberFixture;
	@Autowired
	LoadOrdererPort loadOrdererPort;

	@Test
	void loadOrdererTest() {
		// given
		Member member = memberFixture.createMember("이중석", "ejoongseok@gmail.com", "대전광역시 서구");
		// when
		Member orderer = loadOrdererPort.load(member.getId());
		// then
		Assertions.assertThat(orderer.getId()).isEqualTo(member.getId());
	}
	
}
