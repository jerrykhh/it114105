import java.util.Scanner;

class Main{

    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Battleships game = new Battleships();
        game.setScanner(scanner);
        game.start();
    }
}