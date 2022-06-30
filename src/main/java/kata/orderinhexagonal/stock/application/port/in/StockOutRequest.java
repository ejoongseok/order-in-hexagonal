package kata.orderinhexagonal.stock.application.port.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockOutRequest {

	@NotNull(message = "상품 ID를 입력해주세요.")
	private Long itemId;
	@NotNull(message = "상품 출고 수량을 입력해주세요.")
	@Min(value = 1, message = "출고 수량을 1개 이상으로 입력해주세요.")
	private Integer stockOutQuantity;

	public StockOutRequest(Long itemId, int stockOutQuantity) {
		this.itemId = itemId;
		this.stockOutQuantity = stockOutQuantity;
	}

	public static StockOutRequest of(Long itemId, int stockOutQuantity) {
		return new StockOutRequest(itemId, stockOutQuantity);
	}
}
