
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

    private Stack<LunchSetMemento> undoList;

    public Caretaker() {
        undoList = new Stack();
    }

    public void saveLunchSet(LunchSetMemento lunchSetMemento) {
        undoList.push(lunchSetMemento);
    }

    public void undo() {
        if (undoList.isEmpty()) {
            System.out.println("UndoList is Empty");
            return;
        }
        LunchSetMemento obj = undoList.pop();

        obj.restore();
    }

}
