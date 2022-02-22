public class Main {
    public static void main(String[] args) {
        BingoGameBoard game = new BingoGameBoard();
        game.regPlayer(new Player("1"));
        game.regPlayer(new Player("2"));
        game.start();
    }
}