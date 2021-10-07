public class Q6gExtra {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            for (int s = 0; s < i; s++)
                System.out.print("  ");
            for( int j = 5-i; j > 0; j--)
                System.out.print(j + " ");
            System.out.println();
        }
    }
}
