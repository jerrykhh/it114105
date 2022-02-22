import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double r, l;
        System.out.print("Enter radius: ");
        r = input.nextDouble();
        System.out.print("Enter length: ");
        l = input.nextDouble();
        System.out.printf("volume = %.2f\n", (r*r*Math.PI*l) );
    }
}
