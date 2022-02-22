public class Rectangle extends Shape{
    private Point topLeft;
    private double width;
    private double height;

    public Rectangle(double width, double height, double x, double y) {
        super("rectangle");
        topLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public String toString() {
        return "top left=" + topLeft.toString() + "; width=" + width + "; height=" + height;
    }
}
