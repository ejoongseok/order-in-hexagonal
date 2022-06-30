package kata.orderinhexagonal.stock.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockOutResponse {
	private long id;
	private int quantity;
	private long itemId;
	private String itemName;

	public StockOutResponse(Long id, Integer quantity, Long itemId, String itemName) {
		this.id = id;
		this.quantity = quantity;
		this.itemId = itemId;
		this.itemName = itemName;
	}
}
