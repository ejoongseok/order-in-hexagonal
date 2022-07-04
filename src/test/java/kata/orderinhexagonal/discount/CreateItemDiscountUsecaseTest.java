package kata.orderinhexagonal.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.domain.DiscountType;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

class CreateItemDiscountUsecaseTest {

	CreateItemDiscountUsecase createItemDiscountUsecase = new DiscountService();
	ItemFixture itemFixture;

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

	private static class DiscountService implements CreateItemDiscountUsecase {

		private final DiscountItemLoadPort discountItemLoadPort = new DiscountItemLoadPort();

		@Override
		public Discount createItemDiscount(CreateItemDiscountRequest request) {
			Item discountItem = discountItemLoadPort.load(request.getItemId());
			Discount discount = new Discount(request.getDiscountType(), request.getDiscountRate(), discountItem);
			return discount;
		}
	}

	private static class Discount {

		private Long id;
		private DiscountType discountType;
		private Item item;
		private int discountValue;

		public Discount(DiscountType discountType, int discountRate, Item discountItem) {
			this.discountType = discountType;
			this.discountValue = discountRate;
			this.item = discountItem;
		}

		public long getId() {
			return id;
		}

		public DiscountType getDiscountType() {
			return discountType;
		}

		public Item getItem() {
			return item;
		}

		public int getDiscountValue() {
			return discountValue;
		}
	}
}
