public class Player {
    
    private BingoGameCard card;
    private String name;

    public Player(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public void setBingoGameCard(BingoGameCard card){
        this.card = card;
    }

    public BingoGameCard getBingoGameCard(){
        return card;
    }
}
