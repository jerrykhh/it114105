import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("n? ");
        int num = input.nextInt();
        int result = 1;
        for(int i=num; i > 0; i--){
            System.out.printf("%d ", i);
            if(i != 1)
                System.out.print("x ");
            else
                System.out.print("= ");
            result*=i;
        }
        System.out.println(result);
    }
}
