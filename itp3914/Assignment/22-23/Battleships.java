import java.util.Scanner;

public class Battleships {

    public final static int DEFAULT_GAME_BOARD_SIZE = 10;
    public final static int DEFAULT_GAME_NUM_GROUP_SHIP = 3;

    private int[][] board;
    private Scanner input;
    private int launched = 1, hit = 0;

    // -1 missing
    // 0 null
    // 1 hit
    // 2 ship

    public Battleships() {
        board = new int[DEFAULT_GAME_BOARD_SIZE][DEFAULT_GAME_BOARD_SIZE];
        setShips();
    }

    public Battleships(int size) {
        board = new int[size][size];
        setShips();
    }

    public Battleships(int size, Ship[] ships) {
        board = new int[size][size];
        setShips(ships);
    }

    public void setScanner(Scanner scanner) {
        this.input = scanner;
    }

    public void start() {
        System.out.println("---- Battleship Game----");
        showBoard(false);
        while (!isWin()) {

            System.out.print("Set your missile [XY] (x to exit) :");
            char[] misile = input.nextLine().toCharArray();

            if (misile.length == 1) {
                if (misile[0] == 'c')
                    showBoard(true);
                else if (misile[0] == 'x') {
                    System.out.println("Bye!");
                    System.exit(1);
                } else
                    System.out.println("Mismatch!!");

            } else if (misile.length == 2) {
                int x = Integer.parseInt(String.valueOf(misile[0]));
                int y = Integer.parseInt(String.valueOf(misile[1]));
                put(x, y);
                showBoard(false);
                showStatus();
            } else {
                System.out.println("Mismatch!!");
            }
        }
        System.out.println("You have hit all battleships!");
    }

    public void put(int x, int y) {
        int val = board[x][y];
        if (val == 0) {
            System.out.println("Missed.");
            board[x][y] = -1;
            launched++;
        } else if (val == 2) {
            System.out.println("It's a hit!!");
            board[x][y] = 1;
            hit++;
            launched++;
        } else {
            System.out.println("You have already fired this area.");
        }
    }

    public void setShips(Ship[] ships) {

        for (Ship ship : ships) {
            this.board[ship.x][ship.y] = 2;
        }
    }

    public void setShips() {
        setShips(createShip(this.board));
    }

    public static Ship[] createShip(int[][] boards) {
        return createShip(boards, DEFAULT_GAME_NUM_GROUP_SHIP, DEFAULT_GAME_NUM_GROUP_SHIP);
    }

    public static Ship[] createShip(int[][] board, int numOfGroupShip, int numOfShip) {

        Ship[] ships = new Ship[numOfGroupShip * numOfShip];

        for (int i = 0; i < numOfGroupShip * numOfShip; i += 3) {
            int count = 0;
            while (count++ < numOfShip) {

                int cX;
                int cY;
                boolean isHorizontally = (Math.random() > 0.5);

                do {
                    cX = (int) Math.ceil(Math.random() * (board.length - 1 - 2));
                    cY = (int) Math.ceil(Math.random() * (board[0].length - 1 - 2));
                    ships[i] = new Ship(cX, cY);
                } while ((!isHorizontally && board[cX][cY] == 2 || board[cX + 1][cY] == 2 || board[cX + 2][cY] == 2)
                        || (isHorizontally && board[cX][cY] == 2 || board[cX][cY + 1] == 2 || board[cX][cY + 2] == 2));

                for (int j = 0; j < numOfShip; j++) {
                    if (!isHorizontally)
                        ships[i + j] = new Ship(cX + j, cY);
                    else
                        ships[i + j] = new Ship(cX, cY + j);
                }

            }
        }

        return ships;

    }

    public void showStatus() {
        System.out.printf("Launched:%d, Hit: %d\n", launched, hit);
    }

    public boolean isWin() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int val = board[i][j];
                if (val == 2)
                    return false;
            }
        }
        return true;
    }

    public void showBoard(boolean showHidden) {
        System.out.println();

        int size = Integer.toString(board.length - 1).length();
        if (size < 2)
            size++;
        for (int i = 0; i < size + 1; i++)
            System.out.print(" ");
        System.out.print(" ");
        for (int i = 0; i < board.length; i++)
            System.out.printf(" %d", i);
        System.out.println();

        for (int i = 0; i < size + 1; i++)
            System.out.print("-");
        System.out.print("+-");

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < Integer.toString(i).length() + 1; j++)
                System.out.print("-");
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.printf("%d", i);

            for (int j = 0; j < size - Integer.toString(i).length() + 1; j++)
                System.out.print(" ");

            System.out.print("| ");

            for (int j = 0; j < board[i].length; j++) {

                int val = board[i][j];
                if (val == 1)
                    System.out.print("#");
                else if (val == -1)
                    System.out.print("O");
                else if (showHidden && val == 2)
                    System.out.print("S");
                else
                    System.out.print(".");

                for (int k = 0; k < Integer.toString(j).length(); k++)
                    System.out.print(" ");
            }

            System.out.println();
        }

    }

}

class Ship {
    public int x;
    public int y;

    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
