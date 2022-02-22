public class TestShape {
    public static void main(String[] args) {
        Shape[] myShapes = new Shape[3];
        myShapes[0] = new Circle(10.5, 20, 25);
        myShapes[1] = new Rectangle(30.5, 23.5, 15.5, 20.5);
        myShapes[2] = new Circle(8, 9.5, 10.5);
        for (int i = 0; i < myShapes.length; i++) {
            System.out
                    .println(myShapes[i].getName() + "=" + myShapes[i].toString() + "; Area=" + myShapes[i].getArea());
        }
    }
}