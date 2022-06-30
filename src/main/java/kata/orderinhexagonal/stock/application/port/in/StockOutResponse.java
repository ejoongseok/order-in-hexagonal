package kata.orderinhexagonal.stock.application.port.in;

public class StockOutResponse {
	private long id;
	private int quantity;
	private long itemId;
	private String itemName;

	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getItemId() {
		return itemId;
	}

	public String getItemName() {
		return itemName;
	}
}
