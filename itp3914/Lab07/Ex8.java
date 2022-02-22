import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("n? ");
        int num = input.nextInt();
        int result = 1;
        for(int i=num; i > 0; i--)
            result*=i;
        
        System.out.printf("%d! = %d\n", num, result);
    }
}
