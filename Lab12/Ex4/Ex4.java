public class Ex4 {
	public static void main(String [] args) {
		Food f = new Food("Rice", 3);
		Drink d = new Drink("Pepsi", 7, 250);
		Coffee c = new Coffee("Cappuccino", 13, 200, true);
 
		System.out.println(f);
		System.out.println(d);
		System.out.println(c);
	}
}
