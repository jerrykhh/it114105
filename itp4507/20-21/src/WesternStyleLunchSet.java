/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
public class WesternStyleLunchSet extends LunchSet {

    public WesternStyleLunchSet(String mainDish, int price, int availableCount) {
        super(mainDish, price, availableCount);
    }

    public String getSideDish() {
        return "rice/spaghetti/French fries";
    }

    public static String getDrink(String drinkCode) {
        String drink = "";
        if (drinkCode.charAt(0) == 'i') {
            drink = "Iced ";
        } else {
            drink = "Hot ";
        }
        if (drinkCode.charAt(1) == 't') {
            drink += "Tea";
        } else {
            drink += "Coffee";
        }
        return drink;
    }

    public static String getSide(String sideCode) {
        if (sideCode.equals("r")) {
            return "rice";
        } else if (sideCode.equals("s")) {
            return "spaghetti";
        } else {
            return "French fries";
        }
    }

    public String getDetails() {
        return "Western style Business Set Lunch\n"
                + "main dish: " + super.getMainDish() + "\n"
                + "with " + getSideDish() + "\n"
                + "price: " + super.getPrice() + "\n"
                + "available count: " + super.getAvailableCount();
    }

    @Override
    public LunchSetWesternStyleMemento saveMemento() {
        return new LunchSetWesternStyleMemento(this);
    }
}
