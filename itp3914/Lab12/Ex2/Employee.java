public class Employee {
	public static final int MIN_ID = 1000;
	protected String name;
	protected int employeeID;
 
	public Employee(String n, int id) {
		name = n;
		if (id < MIN_ID) 
			employeeID = 0;
		else
			employeeID = id;
	}
 
	public String toString() {
		return "Name: " + name + ", ID: " + employeeID;
	}
}
