public class Item {
	private String productCode;
	private double price;
	private int quantity;
 
	public Item(String productCode, double price, int quantity) {
        this.productCode = productCode;
        this.price = price;
        this.quantity = quantity;
	}
 
	public double getItemTotal() {
		return price * quantity;
	}
 
	public String toString() {
		return productCode +":" + price + "*" + quantity + "=" + getItemTotal();
	}
}
