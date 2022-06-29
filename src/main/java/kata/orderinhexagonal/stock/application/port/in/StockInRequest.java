package kata.orderinhexagonal.stock.application.port.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockInRequest {
	@NotNull(message = "상품 ID를 입력해주세요.")
	@Min(value = 1, message = "잘못된 상품 ID 입니다.")
	private Long itemId;
	@NotNull(message = "상품 수량을 입력해주세요.")
	@Min(value = 1, message = "입고 수량을 1개 이상으로 입력해주세요.")
	private Integer quantity;

	public StockInRequest(Long itemId, int quantity) {

		this.itemId = itemId;
		this.quantity = quantity;
	}

	public static StockInRequest of(Long itemId, int quantity) {
		return new StockInRequest(itemId, quantity);
	}
}
