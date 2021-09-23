import java.util.Scanner;
public class Ex7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("? ");
        int num = input.nextInt();
        if(isPrime(num))
            System.out.printf("%d is a prime number\n", num);
        else
            System.out.printf("%d is not a prime number\n", num);
    }

    public static boolean isPrime(int num) {
        if(num==2)
            return true;
        
        for (int i = 2; i < num; i++){
            if (num%i == 0)
                return false;
        }
        return true;
    }
}
