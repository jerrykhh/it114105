public class Drink extends Food {
    protected int volume;
    Drink(String name, int price, int volume){
        super(name, price);
        this.volume = volume;
    }

    @Override
    public void setPrice(int price) {
        if(price < 5)
            super.setPrice(5);
        else
            super.setPrice(price);
    }

    @Override
    public String toString() {
        return super.toString() + ", volume=" + volume;
    }
}
