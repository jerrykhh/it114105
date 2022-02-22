public class Ex3 {
	public static void main(String [] args) {
		Student s1=new Student("Ben", 123, 2);
		System.out.println(s1);
 
		Student s2=new Student("John", 246, 6);
		System.out.println(s2);
 
		Student os1=new OutstandingStudent("Mary", 456, 3, "academic");
		System.out.println(os1);
 
		Student os2=new OutstandingStudent("Peter", 567, 7, "sports");
		System.out.println(os2);
	}
}
