package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;

class LoadItemPortTest {

	ItemFixture itemFixture;
	LoadItemPort loadItemPort;

	@Test
	void loadItemTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		// when
		Item loadItem = loadItemPort.load(item.getId());
		// then
		Assertions.assertThat(loadItem.getId()).isEqaulTo(item.getId());
		Assertions.assertThat(loadItem.getName()).isEqaulTo(item.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqaulTo(item.getPrice());
		Assertions.assertThat(loadItem.getStockQuantity()).isEqaulTo(item.getStockQuantity());
	}

}
