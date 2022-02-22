public class Ex2 {
	public static void main(String [] args) {
		Rectangle r = new Rectangle(1, 2);
		System.out.println("r=" + r);
		System.out.println("area=" + r.area());
 
		r = new Rectangle(3, 4);
		System.out.println("r=" + r);
		System.out.println("area=" + r.area());
 
		r = null;
		System.out.println("r=" + r);
		System.out.println("area=" + r.area());
	}
}
