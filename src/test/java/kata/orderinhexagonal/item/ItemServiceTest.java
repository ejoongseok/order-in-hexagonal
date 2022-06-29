package kata.orderinhexagonal.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import kata.orderinhexagonal.item.domain.Item;

class ItemServiceTest {

	ItemService itemService;

	@Test
	void createItemTest() {
		// given
		String name = "노트북";
		int price = 1_000_000;
		CreateItemRequest request = CreateItemRequest.of(name, price);

		// when
		Item newItem = itemService.createItem(request);
		// then
		Assertions.assertThat(newItem.getId()).isPositive();
		Assertions.assertThat(newItem.getName()).isEqualTo(name);
		Assertions.assertThat(newItem.getPrice()).isEqualTo(price);
		Assertions.assertThat(newItem.getStockQuantity()).isZero();
	}

	private static class ItemService implements CreateItemUsecase {

		private SaveItemPort saveItemPort;

		@Override
		public Item createItem(CreateItemRequest request) {
			Item item = new Item(request);
			saveItemPort.saveItem(item);
			return item;
		}
	}
}
