public class Food {
    protected String name;
    protected int price;

    public Food() {
        name = null;
        price = 0;
    }

    public Food(String name, int price) {
        this.name = name;
        setPrice(price);
    }

    public String toString() {
        return "name=" + name + ", price=" + price;
    }

    public void setPrice(int price) {
        if (price >= 0)
            this.price = price;
        else
            this.price = 0;
    }
}