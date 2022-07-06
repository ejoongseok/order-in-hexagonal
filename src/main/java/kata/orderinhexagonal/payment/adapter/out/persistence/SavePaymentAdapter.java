package kata.orderinhexagonal.payment.adapter.out.persistence;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import kata.orderinhexagonal.order.adapter.out.persistence.OrderRepository;
import kata.orderinhexagonal.order.domain.Order;
import kata.orderinhexagonal.payment.application.port.out.PaymentSavePort;
import kata.orderinhexagonal.payment.domain.Payment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SavePaymentAdapter implements PaymentSavePort {

	private final OrderRepository orderRepository;

	private final PaymentRepository paymentRepository;

	@Override
	@Transactional
	public void save(Payment payment) {
		Order order = payment.getOrder();
		OrderEntity orderEntity = orderRepository.findById(order.getId())
			.orElseThrow(() -> new IllegalArgumentException("Order not found"));
		orderEntity.changeStatus(order.getStatus());
		PaymentEntity paymentEntity = new PaymentEntity(orderEntity, payment.getCardNumber(), payment.getCardCvc(), order.getTotalPrice(), payment.getPaymentType(), payment.getCardType(), payment.getCardCompany(), payment.getStatus());
		paymentRepository.save(paymentEntity);

		payment.assignId(paymentEntity.getId());
	}
}
