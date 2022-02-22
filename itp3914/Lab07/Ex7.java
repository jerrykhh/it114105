import java.util.Scanner;

public class Ex7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Number of stars? ");
        int num = input.nextInt();
        for(int i = 0; i < num; i++){
            System.out.print("*");
        }
    }
}
