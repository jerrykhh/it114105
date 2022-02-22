/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
import java.util.Scanner;

public class Main {
    
    private static Scanner input;
    private static CommandHandler ch;
    
    
    public static void main(String[] args){
        input = new Scanner(System.in);
        ch = initCommand();
        run();
    }
    
    public static void run(){
        while (true) {
          ch.printCommandList();
          String cmd = input.nextLine();
          boolean result = ch.run(cmd);
          if (!result) {
              System.out.println("No Command Match");
          }
          System.out.println("");
      }
    }
    
    public static CommandHandler initCommand() {
        CommandHandler ch = new CommandHandler();
        ch.regCommand("e", new EditMenuCommandFactory(input));
        ch.regCommand("s", new ShowMenuCommandFactory());
        ch.regCommand("p", new PlaceOrderCommandFactory(input));
        ch.regCommand("c", new CancelOrderCommandFactory(input));
        ch.regCommand("l", new ListOrdersCommandFactory());
        ch.regCommand("d", new OrderIsDoneCommandFactory());
        ch.regCommand("q", new ExitCommandFactory());
        return ch;
    }
}
