/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdEditMenu implements Command{
    
    private Scanner input;
    
    public CmdEditMenu(Scanner input){
        this.input = input;
    }
        
    public void execute() {
        try {
            System.out.println("\nEdit Menu");
            System.out.print("Chinese or Western (c | w): ");
            String typeLunchSet = input.nextLine();
            System.out.print("Enter main dish: ");
            String mainDish = input.nextLine();
            System.out.print("Enter price: ");
            int price = Integer.parseInt(input.nextLine());
            System.out.print("Enter available count: ");
            int available = Integer.parseInt(input.nextLine());
            StaffCanteen.getInstance().editMenu(typeLunchSet, mainDish, price, available);
            System.out.println("Menu Updated");
        } catch (LunchSetStyleException ex) {
            System.out.println(ex.getMessage());
        }
        
    }    
}
