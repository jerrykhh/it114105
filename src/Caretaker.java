
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
public class Caretaker {

    private Stack<CoffeeProductMemento> undoList;
    private Stack<CoffeeProductMemento> redoList;

    public Caretaker() {
        undoList = new Stack();
        redoList = new Stack();
    }

    public void saveCoffeeProduct(CoffeeProductMemento xcoffeeProd) {
        redoList.clear();
        undoList.push(xcoffeeProd);
    }

    public void undo() {
        if (undoList.isEmpty()) {
            System.out.println("UndoList is Empty");
            return;
        }
        CoffeeProductMemento obj =  undoList.pop();

        redoList.push(obj.getOrig().saveMemento());
        
        obj.restore();
    }

    public void redo() {
        if (redoList.isEmpty()) {
            System.out.println("RedoList is Empty");
            return;
        }
        CoffeeProductMemento obj = redoList.pop();
        undoList.push(obj.getOrig().saveMemento());
        obj.restore();

    }

}
