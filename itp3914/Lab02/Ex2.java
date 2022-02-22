import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) {
        // Create a Scanner object for console input
        Scanner input = new Scanner(System.in);
        // Declare variables
        double tc, hdlc, tg;

        System.out.print("Enter TC: ");
        tc = input.nextDouble();
        System.out.print("Enter HDLC: ");
        hdlc = input.nextDouble();
        System.out.print("Enter TG: ");
        tg = input.nextDouble();
        System.out.println("result = " + (tc - hdlc - (tg / 5)));
    }
}
