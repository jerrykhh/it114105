public class Ex8 {
    public static void main(String[] args) {
        int[][] mTable = new int[10][10];
        
        for (int i = 0; i < mTable.length; i++) {
            for (int j = 0; j < mTable[i].length; j++) {
                mTable[i][j] = i*j;
            }
        }
        System.out.print("    ");
        for ( int i = 0; i < 10; i++ )
            System.out.printf( "%3d", i );
        System.out.println();
        System.out.print("   +");
        for(int i = 0; i < 10; i++)
            System.out.print("---");
        System.out.println();

        for (int i = 0; i < mTable.length; i++) {
            System.out.printf("%3d|", i);
            for (int col : mTable[i]) 
                System.out.printf("%3d", col);
            System.out.println();
        }
    }
}
