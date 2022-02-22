public class TestShape {
    public static void main(String[] args) { // line 14
        Shape s1 = new Square("Square1", 4); // line 15
        Shape s2 = new Circle("Circle1", 2); // line 16
        System.out.println("Area of circle: " + s2.area()); // line 17
        System.out.println("Area of square: " + s1.area()); // line 18
    }
}