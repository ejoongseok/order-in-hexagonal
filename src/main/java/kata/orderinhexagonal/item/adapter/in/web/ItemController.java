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
import kata.orderinhexagonal.item.application.service.ItemService;
import kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService itemService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CreateItemResponse createItem(@RequestBody @Valid CreateItemRequest request, Errors errors) {
		if (errors.hasErrors()) {
			throw new IllegalArgumentException(errors.getAllErrors().toString());
		}
		Item item = itemService.createItem(request);
		return new CreateItemResponse(item);
	}
}
