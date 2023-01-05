/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class LunchSetWesternStyleMemento implements LunchSetMemento{

    private String mainDish;
    private int price;
    private int availableCount;
    private LunchSet lunchSet;

    public LunchSetWesternStyleMemento(WesternStyleLunchSet lunchSet) {
        this.mainDish = lunchSet.getMainDish();
        this.price = lunchSet.getPrice();
        this.availableCount = lunchSet.getAvailableCount();
        this.lunchSet = lunchSet;
    }

       
    @Override
    public LunchSet getOrig() {
        return lunchSet;
    }

    @Override
    public void restore() {
        lunchSet.setAvailableCount(availableCount);
    }
    
}
