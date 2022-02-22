/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class OrderChineseLunchSet extends Order {
    
    private String drinkCode;
    private LunchSet lunchset;
    
    public OrderChineseLunchSet(String drinkCode, String staffNumber, String officeLocation, LunchSet lunchSet) {
        super(staffNumber, officeLocation);
        this.lunchset = lunchSet;
        this.drinkCode = drinkCode;
    }
    
    public String getOrderDetail(){
        return "C: " + super.toSting() + lunchset.getMainDish() + ", Chinese Soup, " + ChineseStyleLunchSet.getDrink(drinkCode);
    }
}
