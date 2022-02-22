public class Worker {
    private String name;
    protected double salary;

    public Worker(String name) {
        this.name = name;
    }

    public double earnings() {
        return salary;
    }

    public String toString() {
        return name;
    }
}
