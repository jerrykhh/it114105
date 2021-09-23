public class PartTimer extends Employee5 {
    protected int workingHour;
    protected int hourlyRate;

    PartTimer(String name, int employeeID, int workingHour, int hourlyRate){
        super(name, employeeID);
        this.workingHour = (workingHour > 220) ? 0: workingHour;
        this.hourlyRate = hourlyRate;
        calculateSalary();
    }

    protected void calculateSalary(){
        salary = workingHour * hourlyRate;
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + ", Salary: " + salary + ", working Hour: " + workingHour + ", Hourly rate: " + hourlyRate;
    }
}
