import java.util.Scanner;
public class Ex4 {
	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("Your age? ");		
		int manAge = kb.nextInt();
		int wifeAge;
 
		// call idealAge() with manAge as argument
        wifeAge = idealAge(manAge);
		System.out.println("Ideal age of wife = " + wifeAge);
	}
 
	public static int idealAge(int age) {
		return ( age / 2) + 7;
	}
}
