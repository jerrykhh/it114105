public class Ex1 {
    public static void main(String [] args) {
		Invoice inv = new Invoice("A123", 4);
		double total=0;
 
		inv.addItem("U-231", 34.5, 10);
		inv.addItem("J-994", 124.5, 5);
		inv.addItem("K-674", 4.5, 100);
 
		for (int i=0; i<inv.getItemCount(); i++) {
			System.out.println(inv.getItem(i));
			total += inv.getItem(i).getItemTotal();
		}
		System.out.println("Invoice Total = " + total);
	}

}
