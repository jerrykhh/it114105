public class Circle extends Shape { // line 10
    private int radius; // line 11

    public Circle(String name, int r) {
        super(name); // line 12
        radius = r; // line 13
    }

    public double area() {
        return radius * radius * 3.1416;
    }
}