package kata.orderinhexagonal.discount.application.port.out;

public interface CreateDiscountPolicyValidator {
	boolean existsDiscountItemCheck(Long id);
}
