import java.util.Scanner;

public class Ex12 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfOdd=0, numOfEven=0;
        for (int i = 0; i < 5; i++) {
            System.out.print("Value? ");
            int value = input.nextInt();
            if(value % 2 == 0)
                numOfEven++;
            else
                numOfOdd++;
        }
        System.out.println("Number of odd values = " + numOfOdd);
        System.out.println("Number of even values = " + numOfEven);
        
    }
}
