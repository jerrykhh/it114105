public class Q6fExtra {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            for(int s = 0; s < 4-i; s++)
                System.out.print("  ");
            for(int j = 5; j > 4-i; j--)
                System.out.print(" " + j);
            System.out.println();
        }
    }
}
