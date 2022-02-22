import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double a, b, c;
        System.out.print("a? ");
        a = input.nextDouble();
        System.out.print("b? ");
        b = input.nextDouble();
        System.out.print("c? ");
        c = input.nextDouble();

        if(isRightAngledTriangle(a, b, c))
            System.out.println("It is a right-angled triangle");
        else
            System.out.println("It is not a right-angled triangle");

    }

    public static boolean isRightAngledTriangle(double a, double b, double c){
        return (a*a + b*b) == (c*c);
    }
}
