class Circle {
	private double radius;
 
	public Circle(double r) {
		radius = r;
	}
 
	public double area() {
		return radius*radius*Math.PI;
	}
}

class Rectangle {    
    private double length;     
    private double width;
    public Rectangle(double l, double w)  {
         length = l;        
         width = w;    
    }
    public double area()  {        
         return length * width;    
    } 
}

public class Ex1 {
	public static void main(String [] args) {
		Rectangle r = new Rectangle(30.1, 10.2);
		Circle c = new Circle(5.3);
 
		System.out.println("r=" + r);
		System.out.println("c=" + c);
	}
}	
