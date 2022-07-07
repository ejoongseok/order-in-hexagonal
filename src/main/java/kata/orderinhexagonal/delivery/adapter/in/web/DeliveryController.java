package kata.orderinhexagonal.delivery.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.delivery.application.port.in.CreateDeliveryUsecase;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryResponse;
import kata.orderinhexagonal.delivery.domain.Delivery;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/delivery")
@RequiredArgsConstructor
public class DeliveryController {

	private final CreateDeliveryUsecase createDeliveryUsecase;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public DeliveryResponse delivery(@RequestBody @Valid DeliveryRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}

		Delivery delivery = createDeliveryUsecase.create(request);

		return new DeliveryResponse(delivery);
	}
}
