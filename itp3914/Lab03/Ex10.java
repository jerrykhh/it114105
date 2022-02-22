import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, R, n;
        System.out.print("Enter a: ");
        a = input.nextDouble();
        System.out.print("Enter R: ");
        R = input.nextDouble();
        System.out.print("Enter n: ");
        n = input.nextDouble();
        System.out.printf("S = %.1f\n", (a * (Math.pow(R, n)-1)/(R-1)));
    }
}
