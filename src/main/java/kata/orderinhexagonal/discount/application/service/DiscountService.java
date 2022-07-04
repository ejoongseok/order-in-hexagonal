package kata.orderinhexagonal.discount.application.service;

import org.springframework.stereotype.Service;

import kata.orderinhexagonal.discount.application.port.out.CreateDiscountPolicyValidator;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountRequest;
import kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import kata.orderinhexagonal.discount.application.port.out.SaveDiscountPort;
import kata.orderinhexagonal.discount.domain.Discount;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscountService implements CreateItemDiscountUsecase {

	private final DiscountItemLoadPort discountItemLoadPort;
	private final CreateDiscountPolicyValidator createDiscountPolicyValidator;
	private final SaveDiscountPort saveDiscountPort;

	@Override
	public Discount createItemDiscount(CreateItemDiscountRequest request) {
		Item discountItem = discountItemLoadPort.load(request.getItemId());
		if (createDiscountPolicyValidator.existsDiscountItemCheck(discountItem.getId())) {
			throw new IllegalArgumentException("해당 상품의 할인 정책이 이미 존재합니다.");
		}
		Discount discount = new Discount(request.getDiscountType(), request.getDiscountRate(), discountItem);

		saveDiscountPort.save(discount);

		return discount;
	}
}
