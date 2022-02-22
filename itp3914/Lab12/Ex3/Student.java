public class Student {
	protected String name;
	protected int stid;   // student id
	protected int year;
 
	public Student(String name, int stid, int year) {
		this.name = name;
		this.stid = stid;
		setYear(year);
	}
 
	public void setYear(int year) {
		if (year>0 && year<=3) 
			this.year = year;
		else {
			System.out.println("Wrong input! Year will be set to 1.");
			this.year = 1;
		}
	}
 
	public String toString() {
		return "Name: "+name+", Student ID: "+stid+", Year: "+year;
	}
}
