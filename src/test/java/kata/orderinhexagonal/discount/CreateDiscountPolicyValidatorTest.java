package kata.orderinhexagonal.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.discount.application.port.out.CreateDiscountPolicyValidator;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
class CreateDiscountPolicyValidatorTest {

	@Autowired CreateDiscountPolicyValidator createDiscountPolicyValidator;
	@Autowired
	ItemFixture itemFixture;
	@Test
	void existsDiscountItemCheckTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);

		// when
		boolean result = createDiscountPolicyValidator.existsDiscountItemCheck(item.getId());

		// then
		Assertions.assertThat(result).isFalse();
	}

}
