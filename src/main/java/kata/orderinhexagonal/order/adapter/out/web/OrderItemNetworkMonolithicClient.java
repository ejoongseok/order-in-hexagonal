package kata.orderinhexagonal.order.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OrderItemNetworkMonolithicClient implements OrderItemNetworkClient {

	private final ItemRepository itemRepository;

	private final ItemMapper itemMapper;

	@Override
	public Item getOrderItem(Long id) {
		ItemEntity itemEntity = itemRepository.findItemAndDiscountFetchById(id)
			.orElseThrow(() -> new RuntimeException("Item not found"));

		return itemMapper.toDomain(itemEntity);
	}
}
