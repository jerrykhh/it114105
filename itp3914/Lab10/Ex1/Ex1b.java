class Ex1b {
    public static void main(String[] args) {
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        int oldSalary;
        // Part 1-2 here
        emp1.setName("Chan Tai Man");
        emp1.setSalary(12000);
        emp2.setName("Tam Pring Shing");
        emp2.setSalary(13500);
        // Part 3 below
        System.out.println("Before-");
        System.out.println("Employee 1: name=" + emp1.getName() + " salary=" + emp1.getSalary());
        System.out.println("Employee 2: name=" + emp2.getName() + " salary=" + emp2.getSalary());
        // Part 4-5 here
        emp1.setSalary((int) (emp1.getSalary()*0.1)+emp1.getSalary());
        emp2.setSalary(((int) (emp2.getSalary()*0.05) + emp2.getSalary()));
        System.out.println("After-");
        System.out.println("Employee 1: name=" + emp1.getName() + " salary=" + emp1.getSalary());
        System.out.println("Employee 2: name=" + emp2.getName() + " salary=" + emp2.getSalary());


    }
}