import java.util.Scanner;

public class Ex6b {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Number? ");
        int num = input.nextInt();
        for(int i = 1; i <= num; i++){
            for( int j = 0; j < num-i; j++)
                System.out.print(" ");
            for (int k = 1; k <=i; k++)
                System.out.print(k);
            System.out.println();
        }
    }
}
