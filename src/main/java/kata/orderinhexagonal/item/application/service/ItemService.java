package kata.orderinhexagonal.item.application.service;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import kata.orderinhexagonal.item.domain.Item;

public class ItemService implements CreateItemUsecase {

	private SaveItemPort saveItemPort;

	@Override
	public Item createItem(CreateItemRequest request) {
		Item item = new Item(request);
		saveItemPort.saveItem(item);
		return item;
	}
}
