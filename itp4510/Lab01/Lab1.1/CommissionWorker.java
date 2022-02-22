public class CommissionWorker extends Worker{
    private double commission;
    private int quantity;

    public CommissionWorker(String name, double c, int q) {
        super(name);
        setCommission(c);
        setQuantity(q);
    }

    public void setCommission(double c) {
        commission = c;
    }

    public void setQuantity(int q) {
        quantity = q;
    }

    public double earnings() {
        salary = commission * quantity;
        return salary;
    }

    public String toString() {
        return super.toString() + " earned commission of $" +

                earnings();

    }
}
