public class AdminPrintOrderList implements AdminFunction{

    private LinkedList orders;

    public AdminPrintOrderList(LinkedList orders){
        this.orders = orders;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        System.out.println("--------------------------------------");
        System.out.println(orders);
        System.out.println("--------------------------------------");
        System.out.println("Total outstanding order:" + orders.count());
    }
    
}
