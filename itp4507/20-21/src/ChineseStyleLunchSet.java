/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
public class ChineseStyleLunchSet extends LunchSet{
    
    public ChineseStyleLunchSet(String mainDish, int price, int availableCount) {
        super(mainDish, price, availableCount);
    }
    
    public static String getDrink(String drinkCode){
        String drink = "";
        switch(drinkCode){
            case "h":
                drink = "Hot Tea";
                break;
            case "i":
                drink = "Iced Tea";
        }
        return drink;
    }
    
    public String getSideDish(){
        return "rice, Chinese soup, Chinese tea";
    }
    
    public String getDetails(){
        return "Chinese style Business Set Lunch\n" +
                "main dish: " + super.getMainDish() + "\n"
                + "with " + getSideDish() + "\n" +
                "price: " + super.getPrice() + "\n"
                + "available count: " + super.getAvailableCount();          
    }
    
    public LunchSetChineseStyleMemento saveMemento(){
        return new LunchSetChineseStyleMemento(this);
    }
    
}
