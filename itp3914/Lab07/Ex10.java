import java.util.Scanner;

public class Ex10 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("How many values to enter? ");
        int size = input.nextInt();
        double sum = 0.0;
        for(int i = 0; i < size; i++){
            System.out.print("Value? ");
            sum += input.nextDouble();
        }
        System.out.println("Average = " + sum/size);

        
    }
}
