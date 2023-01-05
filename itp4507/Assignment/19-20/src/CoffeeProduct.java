/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public abstract class CoffeeProduct {

    private String name;
    private int productID;
    private int qty;

    public CoffeeProduct(String name, int productID) {
        this.name = name;
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }

    public int getQty() {
        return qty;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "ID: " + getProductID() + "\nName: " + getName() + "\nQuantity: " + getQty() + "\n";
    }

    public String printList() {
        return getProductID() + "\t" + getName() + "\t" ;
    }
    
    public abstract CoffeeProductMemento saveMemento();

}
