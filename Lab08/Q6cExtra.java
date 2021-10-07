public class Q6cExtra {
    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){

            for(int s = 0; s < i; s++)
                System.out.print("  ");
            

            for(int j = i+1; j <= 5; j++)
                System.out.print(" "+j);
            System.out.println();
        }
    }
}
