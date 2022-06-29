package kata.orderinhexagonal.stock.application.port.in;

import lombok.Getter;

@Getter
public class StockInRequest {
	private final Long itemId;
	private final int quantity;

	public StockInRequest(Long itemId, int quantity) {

		this.itemId = itemId;
		this.quantity = quantity;
	}

	public static StockInRequest of(Long itemId, int quantity) {
		return new StockInRequest(itemId, quantity);
	}
}
