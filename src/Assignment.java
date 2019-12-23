
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public class Assignment {
    private static Scanner input;
    private static ArrayList<CoffeeProduct> productList;
    private static StateManager stateMang;
    private static CommandHandler ch;

    public static void main(String[] args) {
        input = new Scanner(System.in);
        productList = createList();
        stateMang = createStateManager();
        ch = createCommand();
        run();
    }

    public static void run(){
      while (true) {
          System.out.println("Coffee Inventory Management System");
          ch.printCommandList();
          String cmd = input.nextLine();
          boolean result = ch.run(cmd);
          if (!result) {
              System.out.println("No Command Match");
          }
          System.out.println("");
      }
    }

    public static CommandHandler createCommand() {
        CommandHandler ch = new CommandHandler(stateMang);
        ch.regCommand("a", new AddProductCommandFactory(input, productList));
        ch.regCommand("v", new ViewProductCommandFactory(input, productList));
        ch.regCommand("c", new CollectProductCommandFactory(input, productList, stateMang));
        ch.regCommand("s", new SendCoffeeProductCommandFactory(input, productList, stateMang));
        ch.regCommand("u", new UndoLastCommandFactory(stateMang));
        ch.regCommand("r", new RedoLastCommandFactory(stateMang));
        ch.regCommand("sl", new ViewUndoRedoListCommandFactory(stateMang));
        ch.regCommand("x", new ExitCommandFactory());
        return ch;
    }

    public static StateManager createStateManager() {
        return new StateManager();
    }

    private static ArrayList<CoffeeProduct> createList(){
      return new ArrayList<CoffeeProduct>();
    }
}
