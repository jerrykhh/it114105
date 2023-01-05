/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
public abstract class LunchSet {
    private String mainDish;
    private int price;
    private int availableCount;

    public LunchSet(String mainDish, int price, int availableCount) {
        this.mainDish = mainDish;
        this.price = price;
        this.availableCount = availableCount;
    }

    public String getMainDish() {
        return mainDish;
    }

    public int getPrice() {
        return price;
    }

    public int getAvailableCount() {
        return availableCount;
    }
    
    public void setAvailableCount(int availableCount){
        this.availableCount = availableCount;
    }

    public void setMainDish(String mainDish) {
        this.mainDish = mainDish;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    
    public void reduceOneAvailableCount(){
        availableCount--;
    }
    
    public abstract String getSideDish();
    public abstract String getDetails();
    public abstract LunchSetMemento saveMemento();
}
