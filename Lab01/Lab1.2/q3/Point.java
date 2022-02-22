public class Point {
    private double x, y;

    public Point(double a, double b) {
        setPoint(a, b);
    }

    public void setPoint(double a, double b) {
        x = a;
        y = b;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}