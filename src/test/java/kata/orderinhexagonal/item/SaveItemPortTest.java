package kata.orderinhexagonal.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.item.adapter.out.persistence.CreateItemAdapter;
import kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
class SaveItemPortTest {

	@Autowired SaveItemPort saveItemPort;

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
