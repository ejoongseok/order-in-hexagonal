package kata.orderinhexagonal.delivery.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import kata.orderinhexagonal.delivery.application.port.in.DeliveryResponse;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public DeliveryResponse delivery(@RequestBody @Valid DeliveryRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}

		return new DeliveryResponse();
	}
}
