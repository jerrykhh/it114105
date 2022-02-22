public class Ex5 {
    public static void main(String[] args) {
        Employee5[] emps = new Employee5[4];
        emps[0] = new Employee5("Sam", 111, 30000);
        emps[1] = new PartTimer("Ray", 222, 30, 300);
        emps[2] = new NewPartTimer("June", 333, 40, 100, 0.05);
        emps[3] = new NewPartTimer("May", 444, 100, 100, 0.05);
        for (Employee5 employee : emps) 
            System.out.println(employee);
        
    }
}
