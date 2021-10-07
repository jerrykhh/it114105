import java.util.Scanner;

public class Ex9 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter x (in radians): ");
        double result = 0.0;
        double x = result = input.nextDouble();
        System.out.println("Term 1 = " + x);
        for(int i = 3; i <= 27; i+=2){
            double factorial = 1;
            for(int j = 2; j <= i; j++)
                factorial *= j;
            
            double value = Math.pow(x, i)/factorial;
            int term = i/2;
            if((term %2) == 0){
                result += value;
                System.out.println("Term " + (term+1) + " = " + value);    
            }else{
                result -= value;
                System.out.println("Term " + (term+1) + " = -" + value); 
            }
        }
        System.out.println("Sin(" + x + ")=" + result);
    }

}
