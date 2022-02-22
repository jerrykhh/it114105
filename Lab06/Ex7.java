import java.util.Scanner;
public class Ex7 {

    final static int[] HOURLY_RATE = {15, 35, 50};
    public static void main(String[] args) {

        int type, hours;

        Scanner input = new Scanner(System.in);
        System.out.print("Vehicle Type [1=private, 2=bus, 3=truck]? ");
        type = input.nextInt();
        System.out.print("Number of hours? ");
        hours = input.nextInt();
        System.out.println("Parking fee = " + HOURLY_RATE[type-1]*hours);

    }
}
