public class BingoGameCard {
    
    int[][] card;
    private final static int[][][] CARD = {
        {
            {24,2,8,1,25},
            {12,16,7,17,15},
            {5,6,20,19,13},
            {14,23,22,4,3},
            {10,18,11,21,9}
        },
        {
            {24,21,17,15,6},
            {10,3,8,18,20},
            {14,7,16,12,5},
            {25,23,13,19,11},
            {22,4,9,1,2}
        }
    };

    public BingoGameCard(int[][] card){
        this.card = card;
    }

    public void print(){
        for(int i = 0; i < card.length; i++){
            for(int j = 0; j < card[i].length; j++){
                if(card[i][j] == -1)
                    System.out.print(" XX ");
                else
                    System.out.printf(" %2d ", card[i][j]);
            }
            System.out.println();
        }
    }

    public static BingoGameCard generateGameCard(int index){
        if(index % 2 == 0)
            return new BingoGameCard(CARD[1]);
        return new BingoGameCard(CARD[0]);
    }

    public boolean isBingo(){
        for(int i = 0; i < card.length; i++){
            int count = 0;
            for( int j = 0; j < card[i].length; j++){
                if(j>0 && count == 0)
                    break;
                if(card[i][j] == -1)
                    count++;
            }
            if(count == card[i].length)
                return true;
        }


        for(int i = 0; i < card[0].length; i++){
            int count = 0;
            for(int j = 0; j < card.length; j++){

                if(j>0 && count == 0)
                    break;

                if(card[j][i] == -1)
                count++;
            }
            if(count == card.length)
                return true;
        }

        int count = 0;
        for(int i = 0; i < card.length; i++){
            if(i>0 && count == 0)
                break;

            if(card[i][card[i].length-i-1] == -1)
                count++;
        }
        
        if (count == card.length)
            return true;

        count = 0;
        for(int i = 0; i < card.length; i++){
            if(i>0 && count == 0)
                break;

            if(card[i][i] == -1)
                count++;
        }

        if(count == card.length)
            return true;

        return false;
    
    }

    public void replace(int number){
        for(int i = 0; i < card.length; i++){
            for(int j = 0; j < card[i].length; j++){
                if(card[i][j] == number){
                    card[i][j] = -1;
                    break;
                }
            }
        }
    }

}
