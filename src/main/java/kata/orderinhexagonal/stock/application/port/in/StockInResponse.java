package kata.orderinhexagonal.stock.application.port.in;

public class StockInResponse {

	private long id;
	private int quantity;
	private String itemName;

	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getItemName() {
		return itemName;
	}
}
