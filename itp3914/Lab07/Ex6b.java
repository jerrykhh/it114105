
public class Ex6b {
    public static void main(String[] args) {
        // (b)
        int i = 5;
        System.out.println(i--);
        while ( i >= 0){
            System.out.println(i--);
        }
        System.out.println("After loop, i=" + i);
    }

}