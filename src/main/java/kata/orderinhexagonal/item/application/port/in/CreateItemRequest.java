package kata.orderinhexagonal.item.application.port.in;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemRequest {
	@NotBlank(message = "상품 명을 입력해 주세요")
	private String name;

	@Min(value = 1, message = "상품 가격은 1원 이상이어야 합니다")
	@NotNull(message = "상품 가격을 입력해 주세요")
	private Integer price;

	public CreateItemRequest(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public static CreateItemRequest of(String name, int price) {
		return new CreateItemRequest(name, price);
	}
}
