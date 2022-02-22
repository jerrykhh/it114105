public class NewPartTimer extends PartTimer{
    protected int mpf;
    protected double mpfRate;

    NewPartTimer(String name, int employeeID, int workingHour, int hourlyRate, double mpfRate){
        super(name, employeeID, workingHour, hourlyRate);
        this.mpfRate = mpfRate;

        calculateMpf();
    }

    protected void calculateMpf(){
        if(salary >= 6500){
            mpf = (int)(salary * mpfRate);
            if(mpf > 1250)
                mpf = 1250;
            salary = salary - mpf;
        }else
            mpf = 0;
    }

        @Override
        public String toString() {
            // TODO Auto-generated method stub
    
            return super.toString() + ", MPF Rate: " + mpfRate + "%, MPF: " + mpf;
        }
    
}
