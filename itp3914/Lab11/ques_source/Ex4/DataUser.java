class Date {
	private int day;
	private int month;
	private int year;
 
	public Date() {
		this(1, 1, 1970);
	}
 
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
 
	public String toString() {
		return "[" + day + "/" + month + "/" + year + "]";
	}
}


public class DataUser {
    public static void main(String [] args) {
		Date d1 = new Date();
 
		System.out.println(d1);
	}
}
