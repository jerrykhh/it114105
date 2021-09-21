import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, b, c;
        System.out.print("Enter a: ");
        a = input.nextDouble();
        System.out.print("Enter b: ");
        b = input.nextDouble();
        System.out.print("Enter c: ");
        c = input.nextDouble();

        
        if(a == 0)
            System.out.println("This is not a quadratic equation.");
        else{
            double discriminan = b*b - 4*a*c;
            if(discriminan > 0)
                System.out.printf("2 roots, x1=%.1f, x2=%.1f\n", (-b+Math.sqrt(discriminan))/(2*a), (-b-Math.sqrt(discriminan))/(2*a));
            else if (discriminan == 0)
                System.out.printf("1 root, x=%.1f\n", (-b+Math.sqrt(discriminan))/(2*a));
            else
                System.out.println("No real solution");
        }
    }
}
