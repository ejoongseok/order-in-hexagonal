package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.adapter.out.web.LoadItemAdapter;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;

class LoadItemPortTest {

	ItemFixture itemFixture;
	LoadItemPort loadItemPort = new LoadItemAdapter();

	@Test
	void loadItemTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		// when
		Item loadItem = loadItemPort.load(item.getId());
		// then
		Assertions.assertThat(loadItem.getId()).isEqualTo(item.getId());
		Assertions.assertThat(loadItem.getName()).isEqualTo(item.getName());
		Assertions.assertThat(loadItem.getPrice()).isEqualTo(item.getPrice());
		Assertions.assertThat(loadItem.getStockQuantity()).isEqualTo(item.getStockQuantity());
	}

}
