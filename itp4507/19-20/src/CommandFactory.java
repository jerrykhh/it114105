
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
public interface CommandFactory {

    public String getCommandName();

    public Command createCommand();
}

class AddProductCommandFactory implements CommandFactory {

    private Scanner sc;
    private ArrayList productList;

    public AddProductCommandFactory(Scanner sc, ArrayList productList) {
        this.sc = sc;
        this.productList = productList;
    }

    public String getCommandName() {
        return "add product, ";
    }

    public Command createCommand() {
        return new AddProductCommand(sc, productList);
    }
}

class ViewProductCommandFactory implements CommandFactory {

    private Scanner sc;
    private ArrayList productList;

    public ViewProductCommandFactory(Scanner sc, ArrayList productList) {
        this.sc = sc;
        this.productList = productList;
    }

    @Override
    public String getCommandName() {
        return "view products, ";
    }

    @Override
    public Command createCommand() {
        return new ViewProductCommand(sc, productList);
    }

}

class CollectProductCommandFactory implements CommandFactory {

    private Scanner sc;
    private ArrayList productList;
    private StateManager state;

    public CollectProductCommandFactory(Scanner sc, ArrayList productList, StateManager state) {
        this.sc = sc;
        this.productList = productList;
        this.state = state;
    }

    @Override
    public String getCommandName() {
        return "collect product, ";
    }

    @Override
    public Command createCommand() {
        return new CollectProductCommand(sc, productList, state);
    }

}

class SendCoffeeProductCommandFactory implements CommandFactory {

    private Scanner sc;
    private ArrayList productList;
    private StateManager state;

    public SendCoffeeProductCommandFactory(Scanner sc, ArrayList productList, StateManager state) {
        this.sc = sc;
        this.productList = productList;
        this.state = state;
    }

    @Override
    public String getCommandName() {
        return "ship product, \n";
    }

    @Override
    public Command createCommand() {
        return new SendCoffeeProductCommand(sc, productList, state);
    }
}

class UndoLastCommandFactory implements CommandFactory {

    private StateManager state;

    public UndoLastCommandFactory(StateManager state) {
        this.state = state;
    }

    @Override
    public String getCommandName() {
        return "undo, ";
    }

    @Override
    public Command createCommand() {
        return new UndoLastCommand(state);
    }

}

class ViewUndoRedoListCommandFactory implements CommandFactory {

    private StateManager state;

    public ViewUndoRedoListCommandFactory(StateManager state) {
        this.state = state;
    }

    @Override
    public String getCommandName() {
        return "show list undo/redo, ";
    }

    @Override
    public Command createCommand() {
        return new ViewUndoRedoListCommand(state);

    }
}

class RedoLastCommandFactory implements CommandFactory {

    private StateManager state;

    public RedoLastCommandFactory(StateManager state) {
        this.state = state;
    }

    @Override
    public String getCommandName() {
        return "redo, ";
    }

    @Override
    public Command createCommand() {
        return new RedoLastCommand(state);

    }
}

class ExitCommandFactory implements CommandFactory {

    @Override
    public String getCommandName() {
        return "exit system";
    }

    @Override
    public Command createCommand() {
        return new ExitCommand();
    }

}
