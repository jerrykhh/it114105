import java.util.Scanner;

public class Ex11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double sum = 0;
        int count = 0;
        while(true){
            System.out.print("Value? ");
            double value = input.nextDouble();
            if(value == 0)
                break;
            sum+=value;
            count++;
        }
        System.out.println("Average = " + sum/count);
        
    }
}
