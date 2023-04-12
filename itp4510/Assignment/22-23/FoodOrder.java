public class FoodOrder {
	private int memberID;
	private String foodOrder; // A, B, C, or D
	private int priority;

    //constructor	

    public FoodOrder(int memberID){
        this.memberID = memberID;
    }

    public FoodOrder(int memberID, String foodOrder) {
        this.memberID = memberID;
        this.foodOrder = foodOrder;
        priority = 2;

    }
    
    //provide methods getter, setter, toString ....
    public int getMemberID() {
        return this.memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getFoodOrder() {
        return this.foodOrder;
    }

    public void setFoodOrder(String foodOrder) {
        this.foodOrder = foodOrder;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString(){
        return "[ MemberID: " + memberID + " ordered Set " + foodOrder + " with priority " + priority + " ]"; 
    }
    
}