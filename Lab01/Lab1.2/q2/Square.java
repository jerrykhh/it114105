public class Square extends Shape { // line 6
    private int side; // line 7

    public Square(String name, int side) {
        super(name); // line 8
        this.side = side; // line 9
    }

    public double area() {
        return side * side;
    }
}