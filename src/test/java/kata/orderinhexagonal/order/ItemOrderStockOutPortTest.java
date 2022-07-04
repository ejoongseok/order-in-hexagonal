package kata.orderinhexagonal.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kata.orderinhexagonal.fixture.ItemFixture;
import kata.orderinhexagonal.fixture.StockFixture;
import kata.orderinhexagonal.item.domain.Item;

@SpringBootTest
class ItemOrderStockOutPortTest {


	@Autowired ItemFixture itemFixture;

	@Autowired
	StockFixture stockFixture;

	@Autowired ItemOrderStockOutPort itemOrderStockOutPort;

	@Test
	void 재고_차감() {
		// given
		Item item = itemFixture.createItem("가방", 100_000);
		stockFixture.stockIn(item, 10);
		int stockOutQuantity = 3;
		// when
		itemOrderStockOutPort.stockOut(item, stockOutQuantity);
		// then
		Item refreshItem = itemFixture.getItem(item.getId());
		Assertions.assertThat(refreshItem.getId()).isEqualTo(item.getId());
		Assertions.assertThat(refreshItem.getStockQuantity()).isEqualTo(item.getStockQuantity() - stockOutQuantity);
	}

}