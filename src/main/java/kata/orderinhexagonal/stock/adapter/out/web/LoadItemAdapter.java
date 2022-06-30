package kata.orderinhexagonal.stock.adapter.out.web;

import kata.orderinhexagonal.item.domain.Item;
import kata.orderinhexagonal.stock.application.port.out.LoadItemPort;

public class LoadItemAdapter implements LoadItemPort {
	@Override
	public Item load(Long id) {
		return null;
	}
}
