package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.domain.Item;

class LoadOrderItemPortTest {

	@Test
	void loadItemTest() {
		// given
		Item createItem = itemFixture.createItem();
		// when
		Item loadItem = loadOrderItemPort.loadItem(createItem.getId());

		// then
		Assertions.assertThat(loadItem).isNotNull();
		Assertions.assertThat(loadItem.getId()).isEqualTo(createItem.getId());
		Assertions.assertThat(loadItem.getName()).isEqualTo(createItem.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqualTo(createItem.getPrice());
	}

}
