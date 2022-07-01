package kata.orderinhexagonal.stock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;

@SpringBootTest
class LoadItemPortTest {

	@Autowired ItemFixture itemFixture;
	@Autowired LoadItemPort loadItemPort;

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

	@Test
	void loadItemEntityTest() {
		// given
		Item item = itemFixture.createItem("노트북", 1_000_000);
		// when
		ItemEntity loadItemEntity = loadItemPort.loadEntity(item.getId());
		// then
		Assertions.assertThat(loadItemEntity.getId()).isEqualTo(item.getId());
		Assertions.assertThat(loadItemEntity.getName()).isEqualTo(item.getName());
		Assertions.assertThat(loadItemEntity.getPrice()).isEqualTo(item.getPrice());
		Assertions.assertThat(loadItemEntity.getStockQuantity()).isEqualTo(item.getStockQuantity());
	}


}
