package kata.orderinhexagonal.stock.adapter.in.web;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import kata.orderinhexagonal.stock.application.port.in.StockInResponse;

@RestController
@RequestMapping("/stock")
public class StockController {

	@PostMapping("/in")
	@ResponseStatus(HttpStatus.CREATED)
	public StockInResponse stockIn(@RequestBody @Valid StockInRequest request) {
		return null;
	}
}
