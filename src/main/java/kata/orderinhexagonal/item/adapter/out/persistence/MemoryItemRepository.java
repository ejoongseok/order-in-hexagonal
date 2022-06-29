package kata.orderinhexagonal.item.adapter.out.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import kata.orderinhexagonal.item.domain.Item;

public class MemoryItemRepository implements ItemRepository {
	private final Map<Long, Item> persistenceMap = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(1);
	@Override
	public void save(Item item) {
		item.assignId(sequence.getAndIncrement());
		persistenceMap.put(item.getId(), item);
	}
}
