
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
public class StateManager {
    private Stack redoList;
    private Stack undoList;
    private Caretaker caretaker;
    
    
    public StateManager() {
        undoList = new Stack<>();
        redoList = new Stack<>();
        caretaker = new Caretaker();
    }
    
    public Caretaker getCaretaker(){
        return caretaker;
    }
    public Stack getUndoList() {
        return undoList;
    }

    public Stack getRedoList() {
        return redoList;
    }
}
