
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
public class CmdCancelOrder implements Command{

    private Scanner input;

    public CmdCancelOrder(Scanner input) {
        this.input = input;
    }
    
    @Override
    public void execute() {
       System.out.println("Cancel Order");
       System.out.print("Staff Number: ");
       String staffNumber = input.nextLine();
       StaffCanteen.getInstance().cancelOrder(staffNumber);
    }
    
}
