/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class OrderWesternLunchSet extends Order{
    private String sideCode;
    private String drinkCode;
    private LunchSet lunchSet;

    public OrderWesternLunchSet(String drinkCode, String sideCode, String staffNumber, String officeLocation, LunchSet lunchSet) {
        super(staffNumber, officeLocation);
        this.sideCode = sideCode;
        this.drinkCode = drinkCode;
        this.lunchSet = lunchSet;
    }

   
    public String getSideCode(){
        return sideCode;
    }
    
    public String getOrderDetail(){
        return "W: " + super.toSting() + lunchSet.getMainDish()+ ", " + WesternStyleLunchSet.getSide(sideCode)  +  ", Wetern Soup, " + WesternStyleLunchSet.getDrink(drinkCode);
    }
    
}
