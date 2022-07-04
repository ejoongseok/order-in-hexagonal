package kata.orderinhexagonal.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
class DiscountItemLoadPortTest {

	@Autowired
	ItemFixture itemFixture;
	@Autowired DiscountItemLoadPort discountItemLoad;

	@Test
	void discountItemLoadTest() {
		// given
		Item createItem = itemFixture.createItem("노트북", 1_000_000);
		// when
	    Item loadItem = discountItemLoad.load(createItem.getId());
		// then
		Assertions.assertThat(loadItem.getId()).isPositive();
		Assertions.assertThat(loadItem.getId()).isEqualTo(createItem.getId());
		Assertions.assertThat(loadItem.getName()).isEqualTo(createItem.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqualTo(createItem.getPrice());
		Assertions.assertThat(loadItem.getStockQuantity()).isEqualTo(createItem.getStockQuantity());
	}

}
