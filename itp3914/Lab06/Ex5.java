import java.util.Scanner;

public class Ex5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int a;
        int x = 0;

        System.out.print("a: ");
        a = scan.nextInt();

        switch (a) {
            case 1:
                x += 3;
                break;
            case 2:
                x += 3;
                break;
            case 3:
                x += 5;
                break;
            default:
                x += 7;
                break;
        }

        System.out.println("x = " + x);

    }
}
