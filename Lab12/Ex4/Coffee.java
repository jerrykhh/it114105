public class Coffee extends Drink{
    boolean isSweet;
    Coffee(String name, int price, int volume, boolean isSweet){
        super(name, price, volume);
        this.isSweet = isSweet;
    }

    @Override
    public void setPrice(int price) {
        // TODO Auto-generated method stub
        if(price < 10)
            super.setPrice(10);
        else
            super.setPrice(price);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String sweetStr = "sweet";
        if(!isSweet)
            sweetStr = "not sweet";
        return super.toString() + ", " + sweetStr;
    }
}
