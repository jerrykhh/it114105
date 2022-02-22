import java.util.*;
 
public class Ex3 {
	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		System.out.print("? ");
		int num = kb.nextInt();
		countDown(num);
	}
 
	public static void countDown(int num) {
		for (int i = num; i > 0; i--) 
            System.out.print(i + " ");
        System.out.println();
	}
}
