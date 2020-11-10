
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
public interface CommandFactory {
    public String getCommnadDesc();
    public Command createCommand();
}

class EditMenuCommandFactory implements CommandFactory{

    private Scanner input;
    
    public EditMenuCommandFactory(Scanner input){
        this.input = input;
    }
    
    @Override
    public String getCommnadDesc() {
        return "Edit menu, ";
    }

    @Override
    public Command createCommand() {
        return new CmdEditMenu(input);
    }
    
}

class ShowMenuCommandFactory implements CommandFactory{

    @Override
    public String getCommnadDesc() {
        return "Show menu, ";
    }

    @Override
    public Command createCommand() {
        return new CmdShowMenu();
    }
    
}

class PlaceOrderCommandFactory implements CommandFactory{

    private Scanner input;

    public PlaceOrderCommandFactory(Scanner input) {
        this.input = input;
    }
    
    
    @Override
    public String getCommnadDesc() {
        return "Place order, ";
    }

    @Override
    public Command createCommand() {
        return new CmdPlaceOrder(input);
    }
    
}


class CancelOrderCommandFactory implements CommandFactory{
    private Scanner input;

    public CancelOrderCommandFactory(Scanner input) {
        this.input = input;
    }

    @Override
    public String getCommnadDesc() {
       return "Cancel order, ";
    }

    @Override
    public Command createCommand() {
       return new CmdCancelOrder(input);
    }
    
}

class ListOrdersCommandFactory implements CommandFactory{

    @Override
    public String getCommnadDesc() {
        return "List orders, ";
    }

    @Override
    public Command createCommand() {
        return new CmdListOrder();
    }

}

class OrderIsDoneCommandFactory implements CommandFactory{

    @Override
    public String getCommnadDesc() {
        return "order is Done, ";
    }

    @Override
    public Command createCommand() {
       return new CmdOrderIsDone();
    }
    
}

class ExitCommandFactory implements CommandFactory{

    @Override
    public String getCommnadDesc() {
        return "Quit";
    }

    @Override
    public Command createCommand() {
        return new CmdExit();
    }
    
}
