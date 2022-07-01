package kata.orderinhexagonal.order.application.port.in;

public class OrderItemResponse {
	private long itemId;
	private int quantity;
	private int price;

	public long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPrice() {
		return price;
	}
}
