package kata.orderinhexagonal.discount.adapter.out.persistence;

import org.springframework.stereotype.Component;

import kata.orderinhexagonal.discount.application.port.out.CreateDiscountPolicyValidator;
import kata.orderinhexagonal.discount.application.port.out.SaveDiscountPort;
import kata.orderinhexagonal.discount.domain.Discount;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateDiscountAdapter implements CreateDiscountPolicyValidator, SaveDiscountPort {

	private final DiscountRepository discountRepository;
	private final DiscountMapper discountMapper;

	@Override
	public boolean existsDiscountItemCheck(Long id) {
		return discountRepository.existsById(id);
	}

	@Override
	public void save(Discount discount) {
		DiscountEntity entity = discountMapper.toEntity(discount);
		discountRepository.save(entity);
		discount.assignId(entity.getId());
	}
}
