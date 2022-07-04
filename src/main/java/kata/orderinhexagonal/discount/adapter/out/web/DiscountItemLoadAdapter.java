package kata.orderinhexagonal.discount.adapter.out.web;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class DiscountItemLoadAdapter implements DiscountItemLoadPort {

	private final DiscountItemNetworkClient discountItemNetworkClient;

	@Override
	public Item load(Long itemId) {
		return discountItemNetworkClient.loadDiscountItem(itemId);
	}
}
