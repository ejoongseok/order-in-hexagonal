package kata.orderinhexagonal.stock.application.port.in;

import kata.orderinhexagonal.stock.domain.Stock;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockInResponse {

	private long id;
	private long itemId;
	private int quantity;
	private String itemName;

	public StockInResponse(Long id, Long itemId, Integer quantity, String itemName) {
		this.id = id;
		this.itemId = itemId;
		this.quantity = quantity;
		this.itemName = itemName;
	}
}
