package kata.orderinhexagonal.item.application.port.in;

import kata.orderinhexagonal.item.domain.Item;

public interface CreateItemUsecase {
	Item createItem(CreateItemRequest request);
}
