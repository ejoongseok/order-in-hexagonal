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
import kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import kata.orderinhexagonal.stock.application.port.in.StockOutResponse;
import kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class StockController {

	private final StockInUsecase stockInUsecase;
	private final StockOutUsecase stockOutUsecase;

	@PostMapping("/in")
	@ResponseStatus(HttpStatus.CREATED)
	public StockInResponse stockIn(@RequestBody @Valid StockInRequest request) {
		Stock stock = stockInUsecase.stockIn(request);
		return new StockInResponse(stock.getId(), stock.getItem().getId(), stock.getQuantity(), stock.getItem().getName());
	}

	@PostMapping("/out")
	@ResponseStatus(HttpStatus.OK)
	public StockOutResponse stockOut(@RequestBody @Valid StockOutRequest request) {
		Stock stock = stockOutUsecase.stockOut(request);
		return new StockOutResponse(stock.getId(), stock.getQuantity(), stock.getItem().getId(), stock.getItem().getName());
	}
}
