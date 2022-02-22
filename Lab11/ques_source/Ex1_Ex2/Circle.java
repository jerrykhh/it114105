class Circle {
	private double radius;
 
	public Circle(double r) {
		radius = r;
	}
 
	public double area() {
		return radius*radius*Math.PI;
	}
}
