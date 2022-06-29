package kata.orderinhexagonal.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import kata.orderinhexagonal.item.domain.Item;

class SaveItemPortTest {

	SaveItemPort saveItemPort;

	@Test
	void saveItemTest() {
		// given
		String name = "노트북";
		int price = 1_000_000;
		Item item = new Item(name, price);
		// when
	    saveItemPort.saveItem(item);
		// then
		Assertions.assertThat(item.getId()).isPositive();
		Assertions.assertThat(item.getName()).isEqualTo(name);
		Assertions.assertThat(item.getPrice()).isEqualTo(price);
		Assertions.assertThat(item.getStockQuantity()).isZero();

	}

}
