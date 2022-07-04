package kata.orderinhexagonal.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.discount.domain.DiscountType;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
class CreateItemDiscountUsecaseTest {

	@Autowired CreateItemDiscountUsecase createItemDiscountUsecase;
	@Autowired ItemFixture itemFixture;

	@Test
	void createItemDiscountRateTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);

		int discountRate = 10;
		DiscountType discountType = DiscountType.RATE;
		CreateItemDiscountRequest request = CreateItemDiscountRequest.of(item.getId(), discountType, discountRate);
		// when
		Discount discount = createItemDiscountUsecase.createItemDiscount(request);

		// then
		Assertions.assertThat(discount.getId()).isPositive();
		Assertions.assertThat(discount.getDiscountType()).isEqualTo(discountType);
		Assertions.assertThat(discount.getDiscountValue()).isEqualTo(discountRate);
		Assertions.assertThat(discount.getItem().getId()).isEqualTo(item.getId());
	}
}
