/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class CoffeeProwder extends CoffeeProduct {

    private double wegiht;

    public CoffeeProwder(String name, int productID, double wegiht) {
        super(name, productID);
        this.wegiht = wegiht;
    }

    public double getWegiht() {
        return wegiht;
    }

    public void setWegiht(double wegiht) {
        this.wegiht = wegiht;
    }


    public String toString() {
        return super.toString() + "Wegiht: " + getWegiht() + "\n";
    }

    public String printList() {
        return super.printList() + "\t\t" + getQty() + "\t\t" + (int)(getWegiht()) + "g";
    }
    
    public CoffeeProductMemento saveMemento(){
        return new CoffeeProwderMemento(this);
    }

}
