package kata.orderinhexagonal.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import kata.orderinhexagonal.discount.domain.DiscountType;

@Component
public class DiscountFixture {

	@Autowired
	CreateItemDiscountUsecase createItemDiscountUsecase;

	public void createItemDiscount(Long itemId, DiscountType discountType, int discountRate) {
		CreateItemDiscountRequest request = new CreateItemDiscountRequest(itemId, discountType, discountRate);
		createItemDiscountUsecase.createItemDiscount(request);
	}
}
