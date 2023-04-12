public class AdminRemoveOrder implements AdminFunction{
    
    private int memberId;
    private LinkedList orders;

    public AdminRemoveOrder(int memberId, LinkedList orders) {
        this.memberId = memberId;
        this.orders = orders;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        orders.remove(memberId);
    }

}
