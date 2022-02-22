import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, b, c;
        System.out.print("Please enter a: ");
        a = input.nextDouble();
        System.out.print("Please enter b: ");
        b = input.nextDouble();
        System.out.print("Please enter c: ");
        c = input.nextDouble();
        System.out.printf("x1 = %.2f, x2 = %.2f\n", ((-b+Math.sqrt(b*b - 4*a*c))/(2*a)), ((-b-Math.sqrt(b*b-4*a*c))/(2*a)));

    }
}
