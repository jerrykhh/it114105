import java.util.Scanner;

public class Ex9 {

    final static int[] dollars = {10,5,2,1};
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int amount;
        System.out.print("Input an amount: ");
        amount = input.nextInt();
        for (int dollar : dollars) {
            System.out.printf("%d-dollar coin(s): ", dollar);
            if(amount > dollar){
                System.out.println(amount/dollar);
                amount %= dollar;
            }else{
                System.out.println(0);
            }
        }
    }
}
