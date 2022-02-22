public class Student {                  
	public static int numberOfStudent=0;   	//line 1
	public String name;                   	//line 2	
 
	public Student (String name) {    	//line 3
		numberOfStudent++ ;                	//line 4
		setName(name);                     	//line 5
   }
 
   public int getNumberOfStudent() {       	//line 6
		return numberOfStudent;                            	//line 7
	}
	
	public void setName(String name) {   	//line 8
		this.name = name;                       	//line 9
	}
}
