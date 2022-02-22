/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class CoffeeCandy extends CoffeeProduct {

    private int noOfCandy;
    private int caloriesPerCandy;

    public CoffeeCandy(String name, int productID, int noOfCandy, int caloriesPerCandy) {
        super(name, productID);
        this.noOfCandy = noOfCandy;
        this.caloriesPerCandy = caloriesPerCandy;
    }

    public int getNoOfCandy() {
        return noOfCandy;
    }

    public int getCaloriesPerCandy() {
        return caloriesPerCandy;
    }

    public void setNoOfCandy(int noOfCandy) {
        this.noOfCandy = noOfCandy;
    }

    public void setCaloriesPerCandy(int caloriesPerCandy) {
        this.caloriesPerCandy = caloriesPerCandy;
    }

    @Override
    public String toString() {
        return super.toString() + "Number of candies per package: " + getNoOfCandy() + "\n" + "Calories Per candy: " + getCaloriesPerCandy();
    }

    public String printList() {
        return super.printList() +"\t"+ getQty()+ "\t\t" + getNoOfCandy() + " candy per package (" + getCaloriesPerCandy() + " calories each)";
    }
    
    public CoffeeProductMemento saveMemento(){
        return new CoffeeCandyMemento(this);
    }

}
