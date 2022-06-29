package kata.orderinhexagonal.stock.application.port.in;

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

}
