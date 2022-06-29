package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.service.ItemService;
import kata.orderinhexagonal.item.domain.Item;

@Component
public class ItemFixture {

	@Autowired ItemService itemService;

	public Item createItem(String name, int price) {
		return itemService.createItem(CreateItemRequest.of(name, price));
	}
}
