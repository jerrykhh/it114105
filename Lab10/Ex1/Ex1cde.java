public class Ex1cde {
    public static void main(String[] args) {
        Employee emp1 = new Employee("Chan Tai Main", 12000);
        Employee emp2 = new Employee("Tam Ping Shing", 13500);
        System.out.println("Before-");
        emp1.displayDetails();
        emp2.displayDetails();
        
        emp1.raiseSalary(0.1);
        emp2.raiseSalary(0.05);
        System.out.println("After-");
        emp1.displayDetails();
        emp2.displayDetails();
    }    
}
