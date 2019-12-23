
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jerrykwok
 */
public abstract class Command {

    public abstract boolean execute();
}

interface UndoAllowCommand {

    public void redo();
    public void undo();
}

class AddProductCommand extends Command implements UndoAllowCommand {

    private ArrayList productList;
    private Scanner input;
    CoffeeProduct coffeeProduct;
    

    public AddProductCommand(Scanner input, ArrayList productList) {
        this.input = input;
        this.productList = productList;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter Coffee type (cc=Coffee Candy/cp=Coffee Powder):");
        String type = input.nextLine();
        switch (type) {
            case "cc":

                System.out.println("Enter product id, name, number of candy and calories per candy:");
                String ccValue = input.nextLine();
                String[] ccParameters = ccValue.split(", ");
                if(ccParameters.length==1)
                    ccParameters = ccValue.split(",");
                if (ccParameters.length != 4) {
                    System.out.println("Invalid parameter length");
                    return false;
                }

                try {
                    System.out.println();
                    int productId = Integer.parseInt(ccParameters[0]);
                    String name = ccParameters[1];
                    int noOfCandy = Integer.parseInt(ccParameters[2]);
                    int caloriesPerCandy = Integer.parseInt(ccParameters[3]);
                    if (checkProducrID(productList, productId)) {
                        System.out.println("The id: " + productId + "is already exist");
                        return false;
                    }
                    coffeeProduct = creatCoffeeProduct(name, productId, noOfCandy, caloriesPerCandy);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter: productID, Number of candy and Calories must be Integer");
                    return false;
                }

                break;
            case "cp":
                System.out.println("Enter product Id , name and weight(g):");
                String cpValue = input.nextLine();
                String[] cpParameters = cpValue.split(", ");
                if(cpParameters.length==1)
                    cpParameters = cpValue.split(",");
                if (cpParameters.length != 3) {
                    System.out.println("Invalid parameter length");
                    return false;
                }
                try {
                    int productId = Integer.parseInt(cpParameters[0]);
                    String name = cpParameters[1];
                    int wegiht = Integer.parseInt(cpParameters[2]);
                    if (checkProducrID(productList, productId)) {
                        System.out.println("The id: " + productId + "is already exist");
                        return false;
                    }
                    coffeeProduct = creatCoffeeProduct(name, productId, wegiht);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid parameter: productID, Number of candy and Calories must be Integer");
                    return false;
                }
                break;
            default:
                System.out.println("Invalid Coffee Prodcut Type");
                return false;
        }
        productList.add(coffeeProduct);
        System.out.println("New product record created.\n");
        return true;

    }

    @Override
    public void redo() {
        
        productList.add(coffeeProduct);
    }

    @Override
    public void undo() {
        productList.remove(coffeeProduct);
    }
    

    public static CoffeeProduct creatCoffeeProduct(String name, int productID, int noOfCandy, int caloriesPerCandy) {
        return new CoffeeCandy(name, productID, noOfCandy, caloriesPerCandy);
    }

    public static CoffeeProduct creatCoffeeProduct(String name, int productID, int wegiht) {
        return new CoffeeProwder(name, productID, wegiht);
    }

    public boolean checkProducrID(ArrayList<CoffeeProduct> productList, int id) {
        boolean invalid = false;
        for (CoffeeProduct product : productList) {
            if (product.getProductID() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Add " + coffeeProduct.getProductID() +" " + coffeeProduct.getName();
    }
    
    
    

}

class ViewProductCommand extends Command {

    private ArrayList<CoffeeProduct> productList;
    private Scanner input;

    public ViewProductCommand(Scanner input, ArrayList productList) {
        this.input = input;
        this.productList = productList;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter product Id. (* to show all):");
        String id = input.nextLine();
        System.out.println("");
        if (id.equals("*")) {
            System.out.println("Coffee Product information");
            System.out.println("ID\tName\t\t\t\tQuantity\tOther Info");
            for (CoffeeProduct product : productList) {
                System.out.println(product.printList());
            }
        } else {

            try {
                int productId = Integer.parseInt(id);
                System.out.println("Product information");
                boolean succe = false;
                for (CoffeeProduct product : productList) {
                    if (product.getProductID() == productId) {
                        System.out.println(product);
                        succe = true;
                        break;
                    }
                }
                if (!succe) {
                    System.out.println("Not this ID: " + productId + " Product");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid id, id must be integer only");
                return false;
            }

        }
        return true;
    }
}

class CollectProductCommand extends Command implements UndoAllowCommand {

    private ArrayList<CoffeeProduct> productList;
    private Scanner input;
    private StateManager state;
    private CoffeeProduct coffeeProduct;
    private int productQty;

    public CollectProductCommand(Scanner input, ArrayList<CoffeeProduct> productList, StateManager state) {
        this.productList = productList;
        this.input = input;
        this.state = state;
    }

    @Override
    public boolean execute() {
        int productId;
        
        try {
            System.out.println("Enter product id:");
            String id = input.nextLine();
            productId = Integer.parseInt(id);

        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id must be integer only");
            return false;
        }
        try {
            System.out.println("Quantity to receive:");
            String qty = input.nextLine();
            productQty = Integer.parseInt(qty);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Quantity, Quantity must be integer only");
            return false;
        }
        boolean invalid = true;
        for (CoffeeProduct product : productList) {
            if (product.getProductID() == productId) {
                coffeeProduct = product;
                state.getCaretaker().saveCoffeeProduct(product.saveMemento());
                product.setQty(product.getQty() + productQty);
                System.out.println("Received " + productQty + " packs of " + product.getName() + ". Current quantity is " + product.getQty() + ".");
                invalid = false;
                break;
            }
        }
        if (invalid) {
            System.out.println("No such Coffee Product");
            return false;
        }
        return true;
    }

    @Override
    public void redo() {
        state.getCaretaker().redo();
    }

    @Override
    public void undo() {
        state.getCaretaker().undo();
    }
    
    public String toString(){
        return "Received " + productQty + " " +coffeeProduct.getName() + " ("+coffeeProduct.getProductID()+")";
    }
}

class SendCoffeeProductCommand extends Command implements UndoAllowCommand {

    private ArrayList<CoffeeProduct> productList;
    private Scanner input;
    private StateManager state;
    private CoffeeProduct coffeeProduct;
    private int shipQty;

    public SendCoffeeProductCommand(Scanner input, ArrayList<CoffeeProduct> productList, StateManager state) {
        this.productList = productList;
        this.input = input;
        this.state = state;
    }

    @Override
    public boolean execute() {
        int productId;
        
        try {
            System.out.println("Enter product id:");
            String id = input.nextLine();
            productId = Integer.parseInt(id);

        } catch (NumberFormatException e) {
            System.out.println("Invalid id, id must be integer only");
            return false;
        }
        try {
            System.out.println("Quantity to ship:");
            String qty = input.nextLine();
            shipQty = Integer.parseInt(qty);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Quantity, Quantity must be integer only");
            return false;
        }
        boolean succe = true;
        for (CoffeeProduct product : productList) {
            if (product.getProductID() == productId) {
                if ((product.getQty() - shipQty) < 0) {
                    System.out.println("Invalid quantity (current balance is less than required quantity). Try again!!!");
                    return false;
                }
                coffeeProduct = product;
                state.getCaretaker().saveCoffeeProduct(product.saveMemento());
                product.setQty(product.getQty() - shipQty);
                System.out.println("Shipped " + shipQty +" packs of Premium Coffee Candy. Current quantity is "+ product.getQty()+".");
                succe = false;
                break;
            }
        }
        if (succe) {
            System.out.println("No such Coffee Product");
            return false;
        }
        return true;
    }

    @Override
    public void redo() {
        state.getCaretaker().redo();
    }

    @Override
    public void undo() {
        state.getCaretaker().undo();
    }
    
    public String toString(){
        return "Shipped " + shipQty + " " + coffeeProduct.getName() + " (" + coffeeProduct.getProductID()+ ")";
    }

}

class UndoLastCommand extends Command {

    private StateManager state;

    public UndoLastCommand(StateManager state) {
        this.state = state;
    }

    @Override
    public boolean execute() {
        if (state.getUndoList().isEmpty()) {
            System.out.println("Nothing to undo");
        } else {
            Stack<UndoAllowCommand> UndoList = state.getUndoList();
            UndoAllowCommand undoCmd = UndoList.pop();
            state.getRedoList().push(undoCmd);
            undoCmd.undo();
            System.out.println("undo completed.");

        }
        return true;
    }

}

class RedoLastCommand extends Command {

    private StateManager state;

    public RedoLastCommand(StateManager state) {
        this.state = state;
    }

    @Override
    public boolean execute() {
        if (state.getRedoList().isEmpty()) {
            System.out.println("Nothing to Redo");
        } else {
            Stack<UndoAllowCommand> redoList = state.getRedoList();
            UndoAllowCommand redoCmd = redoList.pop();
            state.getUndoList().push(redoCmd);
            redoCmd.redo();
            System.out.println("Redo completed.");
        }
        return true;
    }        
    
    
}

class ViewUndoRedoListCommand extends Command {

    private StateManager state;

    public ViewUndoRedoListCommand(StateManager state) {
        this.state = state;
    }

    @Override
    public boolean execute() {
        System.out.println("Undo List:");
        if (state.getUndoList().isEmpty()) {
            System.out.println("Empty");
        } else {
            state.getUndoList().forEach(System.out::println);
        }
        System.out.println();

        System.out.println("Redo List:");
        if (state.getRedoList().isEmpty()) {
            System.out.println("Empty");
        } else {
            state.getRedoList().forEach(System.out::println);
        }
        return true;

    }

}

class ExitCommand extends Command {

    @Override
    public boolean execute() {
        System.out.println("Thanks for using Coffee Inventory Management System!!");
        System.exit(0);
        return true;
    }

}
