public class TestWorker {
    public static void main(String [] args) {
        Worker [] w = new Worker[3];
        w[0] = new Worker("Peter");
        w[1] = new CommissionWorker("John", 120, 10);
        w[2] = new HourlyWorker("Mary", 25, 40);
        for( int i = 0; i < w.length; i++)
            System.out.println(w[i]);
    }
}
