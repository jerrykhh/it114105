class Triangle {
	private int base;
	private int height;
 
	public Triangle() {
		base = 0;
		height = 0;
	}
 
	public Triangle(int base, int height) {
		this.base = base;
		this.height = height;
	}
 
	public void printThis() {
		System.out.println(this);
	}
 
	public static void main(String [] args) {
		Triangle t = new Triangle(1, 2);
		System.out.println(t);
		t.printThis();
	}
}
