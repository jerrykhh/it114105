
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public class CmdPlaceOrder implements Command {

    private Scanner input;

    public CmdPlaceOrder(Scanner input) {
        this.input = input;
    }

    @Override
    public void execute() {
        try {
            System.out.println("\nPlace Order");
            System.out.print("Chinese or Western (c | w): ");
            String menuType = input.nextLine();
            if(StaffCanteen.getInstance().searchMenuAvaiable(menuType) == 0){
                System.out.println("Sold Out!");
                return;
            }
            String drinkCode;
            String sideCode = null;
            String staffNumber;
            String officeLocation;
            switch (menuType) {
                case "c":
                    System.out.print("Oolong Tea: hot or iced (h | i): ");
                    drinkCode = input.nextLine();
                    break;
                case "w":
                    System.out.print("side: rice, spaghetti, French fries (r | s | f): ");
                    sideCode = input.nextLine();
                    System.out.print("tea or coffee, hot or iced (ht| it | hc | ic): ");
                    drinkCode = input.nextLine();
                    break;
                default:
                    throw new LunchSetStyleException();
            }
            System.out.print("Staff Number: ");
            staffNumber = input.nextLine();
            System.out.print("Office Location: ");
            officeLocation = input.nextLine();
            Order order = (menuType.equals("c")) ? StaffCanteen.getInstance().placeChineseLunchSetOrder(drinkCode, staffNumber, officeLocation) 
                    : StaffCanteen.getInstance().placeWesternLunchSetOrder(drinkCode, sideCode, staffNumber, officeLocation);
            System.out.println("Order Placed");
        } catch (LunchSetStyleException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
