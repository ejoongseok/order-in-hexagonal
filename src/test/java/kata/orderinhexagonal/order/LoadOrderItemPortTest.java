package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;

@SpringBootTest
class LoadOrderItemPortTest {

	@Autowired ItemFixture itemFixture;
	@Autowired
	LoadOrderItemPort loadOrderItemPort;

	@Test
	void loadItemTest() {
		// given
		Item createItem = itemFixture.createItem("가방", 100_000);
		// when
		Item loadItem = loadOrderItemPort.loadItem(createItem.getId());

		// then
		Assertions.assertThat(loadItem).isNotNull();
		Assertions.assertThat(loadItem.getId()).isEqualTo(createItem.getId());
		Assertions.assertThat(loadItem.getName()).isEqualTo(createItem.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqualTo(createItem.getPrice());
	}

}
