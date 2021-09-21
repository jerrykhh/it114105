import java.util.Scanner;

public class Ex8 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        int r, l;
        System.out.print("Enter r: ");
        r = input.nextInt();
        System.out.print("Enter l: ");
        l = input.nextInt();
        System.out.printf("v = %.4f\n", (r*r) * Math.PI * l);
    }    
}
