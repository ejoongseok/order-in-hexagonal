package kata.orderinhexagonal.item.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemResponse {
	private long id;
	private String name;
	private int price;
	private int stockQuantity;
}
