package kata.orderinhexagonal.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.domain.Item;

class CreateItemUsecaseTest {

	CreateItemUsecase createItemUsecase;

	@Test
	void 상품등록() {
		// given
		String name = "노트북";
		int price = 1_000_000;
		CreateItemRequest request = CreateItemRequest.of(name, price);
		// when
		Item item = createItemUsecase.createItem(request);
		// then
		Assertions.assertThat(item.getId()).isPositive();
		Assertions.assertThat(item.getName()).isEqualTo(name);
		Assertions.assertThat(item.getPrice()).isEqualTo(price);
		Assertions.assertThat(item.getStockQuantity()).isEqualTo(0);
	}

}
