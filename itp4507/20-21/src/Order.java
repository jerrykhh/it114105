/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JerryKwok
 */
public abstract class Order {
    private String staffNumber;
    private String officeLocation;

    public Order(String staffNumber, String officeLocation) {
        this.staffNumber = staffNumber;
        this.officeLocation = officeLocation;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public String getOfficeLocation() {
        return officeLocation;
    }

    
    public String toSting(){
        return getStaffNumber() + ", " + getOfficeLocation() + ", ";
    }
    
    public abstract String getOrderDetail();
     
}
