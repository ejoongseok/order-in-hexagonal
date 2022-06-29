package kata.orderinhexagonal.item.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import kata.orderinhexagonal.item.application.port.in.CreateItemResponse;

@RestController
@RequestMapping("/items")
public class ItemController {


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateItemResponse create(@RequestBody @Valid CreateItemRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}
		return new CreateItemResponse(request.getName(), request.getPrice());
	}
}
