public class PartTimeStaff extends Staff implements Salary {
    private int workingHour;

    public PartTimeStaff(String name, int id, char grade, int workingHour) {
        super(name, id, grade);
        this.workingHour = workingHour;
    }

    public void display() {
        System.out.println("Name: " + name + "; ID: " + id + "; Grade: " + grade + "; Working Hour: " + workingHour + "; Salary: " + computeSalary());
    }

    public int computeSalary() {
        switch (grade) {
            case 'A':
                return SALARY_A;
            case 'B':
                return SALARY_B;
            case 'C':
                return SALARY_C;
            default:
                return SALARY_OTHER;
        }
    }
}