public class Circle extends Shape{
    private double radius;
    private Point center;

    public Circle(double radius, double x, double y){
        super("circle");
        this.radius = radius;
        center = new Point(x, y);
    }

    public double getRadius(){
        return radius;
    }

    public Point getCenter(){
        return center;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    public void setCenter(Point center){
        this.center = center;
    }

    public double getArea(){
        return radius*radius*3.1416;
    }

    public String toString(){
        return "center=" + center.toString() + "; radius=" + radius;
    }
}
