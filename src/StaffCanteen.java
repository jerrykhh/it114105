/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class StaffCanteen {

    private static HashMap<String, LunchSet> menuList;
    private static ArrayList<Order> orderList;
    private static StaffCanteen instance = new StaffCanteen();
    private static Caretaker caretaker;

    private StaffCanteen() {
        menuList = new HashMap<>();
        orderList = new ArrayList<>();
        caretaker = new Caretaker();
    }

    public static StaffCanteen getInstance() {
        return instance;
    }

    public int searchMenuAvaiable(String menuCode) {
        return menuList.get(menuCode).getAvailableCount();
    }

    public LunchSet editMenu(String styleMenu, String mainDish, int price, int availabe) throws LunchSetStyleException {
        LunchSet lunchSet = null;

        switch (styleMenu) {
            case "c":
                lunchSet = new ChineseStyleLunchSet(mainDish, price, availabe);
                break;
            case "w":
                lunchSet = new WesternStyleLunchSet(mainDish, price, availabe);
                break;
            default:
                throw new LunchSetStyleException();
        }

        menuList.put(styleMenu, lunchSet);
        return lunchSet;
    }

    public void printMenu() {
        menuList.keySet().forEach((menuKey) -> {
            System.out.println();
            System.out.println(menuList.get(menuKey).getDetails());
        });
    }

    public Order placeChineseLunchSetOrder(String drinkCode, String staffNumber, String officeLocation) {
        LunchSet chineseLunchset = menuList.get("c");
        OrderChineseLunchSet chineseLunchSetOrder = new OrderChineseLunchSet(drinkCode, staffNumber, officeLocation, chineseLunchset);
        orderList.add(chineseLunchSetOrder);
        saveLunchSet(chineseLunchset);
        chineseLunchset.reduceOneAvailableCount();
        return chineseLunchSetOrder;
    }

    public Order placeWesternLunchSetOrder(String drinkCode, String sideCode, String staffNumber, String officeLocation) {
        LunchSet westernLunchset = menuList.get("w");
        OrderWesternLunchSet westernLunchSetOrder = new OrderWesternLunchSet(drinkCode, sideCode, staffNumber, officeLocation, westernLunchset);
        orderList.add(westernLunchSetOrder);
        saveLunchSet(westernLunchset);
        westernLunchset.reduceOneAvailableCount();
        return westernLunchSetOrder;
    }

    private void saveLunchSet(LunchSet lunchSet) {
        caretaker.saveLunchSet(lunchSet.saveMemento());
    }

    public void listOrder() {
        System.out.println();
        System.out.println("Outstanding Orders");
        orderList.forEach((order) -> {
            System.out.println(order.getOrderDetail());
        });
    }

    public Order orderDone() {
        if (orderList.isEmpty()) {
            System.out.println("No Order");
            return null;
        }

        Order order = orderList.remove(0);
        System.out.println("\nComplete Order");
        System.out.println(order.getOrderDetail());
        System.out.println("Order marked as done");
        return order;
    }

    public void cancelOrder(String staffNumber) {
        Order removeOrder = null;
        Iterator<Order> i = orderList.iterator();
        while (i.hasNext()) {
            Order order = i.next();
            if (order.getStaffNumber().equals(staffNumber)) {
                removeOrder = order;
                i.remove();
            }
        }
        System.out.println(removeOrder.getOrderDetail().substring(3));
        caretaker.undo();
        System.out.println("Order Cancelled");
    }

}
