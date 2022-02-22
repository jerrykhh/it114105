/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public interface CoffeeProductMemento {
    public CoffeeProduct getOrig();
    public void restore();
}

class CoffeeCandyMemento implements CoffeeProductMemento{
    private int noOfCandy;
    private int caloriesPerCandy;
    private int qty;
    private CoffeeCandy mcoffeeCandy;

    public CoffeeCandyMemento(CoffeeCandy mcoffeeCandy) {
        this.noOfCandy = mcoffeeCandy.getNoOfCandy();
        this.caloriesPerCandy = mcoffeeCandy.getCaloriesPerCandy();
        this.qty = mcoffeeCandy.getQty();
        this.mcoffeeCandy = mcoffeeCandy;
    }

    
    @Override
    public CoffeeProduct getOrig() {
        return mcoffeeCandy;
    }

    @Override
    public void restore() {
        mcoffeeCandy.setCaloriesPerCandy(caloriesPerCandy);
        mcoffeeCandy.setNoOfCandy(noOfCandy);
        mcoffeeCandy.setQty(qty);
    }

    
    public String print() {
        return "CoffeeCandyMemento{" + "noOfCandy=" + noOfCandy + ", caloriesPerCandy=" + caloriesPerCandy + ", mcoffeeCandy=" + mcoffeeCandy + '}';
    }
    

}

class CoffeeProwderMemento implements CoffeeProductMemento{

    private double wegiht;
    private int qty;
    private CoffeeProwder mcoffeeProwder;

    public CoffeeProwderMemento(CoffeeProwder mcoffeeProwder) {
        this.wegiht = mcoffeeProwder.getWegiht();
        this.qty = mcoffeeProwder.getQty();
        this.mcoffeeProwder = mcoffeeProwder;
        
    }
            
    
    @Override
    public CoffeeProduct getOrig() {
        return mcoffeeProwder;
    }

    @Override
    public void restore() {
        mcoffeeProwder.setWegiht(this.wegiht);
        mcoffeeProwder.setQty(qty);
    }
 
}