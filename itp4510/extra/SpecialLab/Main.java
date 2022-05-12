interface Payable {
    public double getPayAmt();
}

class Invoice implements Payable{
    private String invid;
    public double getPayAmt(){
        return 0.0;
    }
}

class Employee extends Person implements Payable {
    private String empid;
    public Employee(String empid){
        this.empid = empid;
    }
    public double getPayAmt(){
        return 0.0;
    }
}

abstract class Person {
    private String name;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}

class Customer extends Person {
    private String custid;
    public Address address;

    public Customer(){
        this.address = new Address();
    }
}

class Address {
    private String room;
    private String street;
    public String getAddress(){
        return "";
    }
}