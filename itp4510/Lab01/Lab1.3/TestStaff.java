public class TestStaff {
    public static void main(String args[]) {
        PartTimeStaff p1 = new PartTimeStaff("John", 123, 'B', 20);
        PartTimeStaff p2 = new PartTimeStaff("Mary", 124, 'A', 22);
        p1.display();
        p2.display();
    }
}