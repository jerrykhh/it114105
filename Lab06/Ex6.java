import java.util.Scanner;

public class Ex6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Street number? ");
        if(input.nextInt() % 2 == 0){
            System.out.println("east-bound");
        }else{
            System.out.println("West-bound");
        }
    }
}
