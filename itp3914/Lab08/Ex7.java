import java.util.Scanner;

public class Ex7 {

    final static String[] option = {"Paper", "Scissor", "Stone"};
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("0:Paper 1:Scissor 2:Stone 3:Exit? ");
            int userInput = input.nextInt();
            if(userInput == 3)
                break;
            int cmpInput = (int) (Math.random()*3);
            System.out.printf("You choose %s vs CPU choose %s\n", option[userInput], option[cmpInput]);
            if(userInput == cmpInput)
                System.out.println("It's Draw");
            else {
                if(userInput == 0 && cmpInput == 2 ||
                    userInput == 1 && cmpInput == 0 ||
                    userInput == 2 && cmpInput == 1){
                        System.out.println("You Win");
                    }
                else {
                    System.out.println("You Lose");
                }

            }
        }
        System.out.println("Bye Bye!");
        
    }
}
