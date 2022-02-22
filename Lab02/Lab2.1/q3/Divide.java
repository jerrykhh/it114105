import java.util.Random;

public class Divide {
    public static void main(String[] args) {
        int a, n;
        for (int i = 0; i < 10; i++) {
            try{
                n = (int) (Math.random() * 10);
                a = 100 / n;
                System.out.println("n:" + n + ", a:" + a);
            }catch(ArithmeticException e){
                System.out.println("Division by Zero");
            }
        }
    }
}
