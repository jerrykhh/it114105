class AStudent {
	private String name;
	public int age;
 
	public void setName(String inName) {
		name = inName;
	}
 
	public String getName() {
		return name;
	}

    public void setAge(int age) {
        if(age<0 || age>120){
            this.age = 18;
            System.out.println("Invalid age:" + age + ", reset it to 18");
        }else
            this.age = age;
    }
}
 
public class Ex3 {
	public static void main(String s[]) {
		AStudent stud1 = new AStudent();
		AStudent stud2 = new AStudent();
		stud1.setName("Chan Tai Man");
		stud1.setAge(19);
		stud2.setName("Ng Hing");
		stud2.setAge(-23);
		System.out.println("Student: name="+stud1.getName() + 
							", age=" + stud1.age);
		System.out.println("Student: name="+stud2.getName() + 
							", age=" + stud2.age);
	}
}
