package kata.orderinhexagonal.item;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;

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

	private static class Item {
		private Long id;
		private String name;
		private Integer price;
		private Integer stockQuantity;

		public Long getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public Integer getPrice() {
			return price;
		}

		public Integer getStockQuantity() {
			return stockQuantity;
		}
	}
}
