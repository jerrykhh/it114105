public class Invoice {
	private String invNumber;
	private Item [] itemList;
	private int itemCount;
	
	public Invoice(String invNumber, int itemNum) {
		// 1. Set instance variable invNumber
		// 2. Create an array of Item with number of elements specified 
		//    by parameter itemNum. Refer to Topic 4.6, Slide 9
		// 3. Set itemCount to 0, as there is no Item initially.
		this.invNumber = invNumber;
        itemList = new Item[itemNum];
        itemCount = 0;
	}
 
	public String getInvNumber() {
		return invNumber;
	}
 
	public Item[] getItemList() {
		return itemList;
	}
	
	public int getItemCount() {
		return itemCount;
	}
 
	public Item getItem(int index) {
		return itemList[index];
	}
 
	public void addItem(String productCode, double price, int quantity) {
		if (itemCount < itemList.length) {
			// create a new Item; Refer to Topic 4.6, Slide 9
			// save item to appropriate element in itemList
			itemList[itemCount] = new Item(productCode, price, quantity);
			itemCount++;
		} else {
			System.out.println("Failed to add new item; max already");
		}
	}
}
