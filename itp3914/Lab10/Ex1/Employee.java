public class Employee {
    String name;
    int salary;

    Employee(String name, int salary){
        this.name = name;
        this.salary = salary;
    }

    public Employee() {}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSalary() {
        return salary;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void displayDetails(){
        System.out.printf("Employee 1: name=%s salary=%d\n", name, salary);
    }

    public void raiseSalary(double perc){
        salary = (int)(salary * perc) + salary;
    }

}
