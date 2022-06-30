package kata.orderinhexagonal.stock.application.port.out;

import kata.orderinhexagonal.item.domain.Item;

public interface LoadItemPort {
	Item load(Long id);
}
