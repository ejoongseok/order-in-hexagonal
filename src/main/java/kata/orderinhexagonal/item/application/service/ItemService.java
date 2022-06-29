package kata.orderinhexagonal.item.application.service;

import kata.orderinhexagonal.item.adapter.out.persistence.CreateItemAdapter;
import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import kata.orderinhexagonal.item.domain.Item;

public class ItemService implements CreateItemUsecase {

	private SaveItemPort saveItemPort = new CreateItemAdapter();

	@Override
	public Item createItem(CreateItemRequest request) {
		Item item = new Item(request.getName(), request.getPrice());
		saveItemPort.saveItem(item);
		return item;
	}
}
