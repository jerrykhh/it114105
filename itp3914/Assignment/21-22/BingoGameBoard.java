import java.util.Scanner;

public class BingoGameBoard {

    private Scanner input;
    private List playerList;
    private List records;

    public BingoGameBoard(){
        input = new Scanner(System.in);
        playerList = new List();
        records = new List();
    }

    public void regPlayer(Player player){
        playerList.append(player);
        player.setBingoGameCard(BingoGameCard.generateGameCard(playerList.getSize()));
    }

    public void printGameBoard(){
        for(int i = 0; i < playerList.getSize(); i++){
            Player player = (Player) playerList.getItemAt(i);
            System.out.printf("Player%s's Card:\n", player.getName());
            player.getBingoGameCard().print();
        }
        System.out.println();
    }

    public boolean hasWin(){
        for(int i = 0; i < playerList.getSize(); i++){
            Player player = (Player) playerList.getItemAt(i);
            if(player.getBingoGameCard().isBingo())
                return true;
        }
        return false;
    }

    public int getUserInput(){
        int num = -1;
        do{ 
        
            try{
                System.out.print("Game Host call (0 to exit): ");
                num =  Integer.parseInt(this.input.nextLine());
                if(num < 0 || num > 25)
                    throw new NumberFormatException();

                if(duplicateHistory(num))
                    throw new NumberRepeatException(num);

            }catch(NumberFormatException e){
                System.out.println("The number must be between 1 to 25, please call again!");
            }catch(NumberRepeatException e){
                System.out.println(e.getMessage());
                num = -1;
            }
        }while(!(num < 26 && num > -1));
        return num;
    }

    public boolean duplicateHistory(int num){
        for(int i = 0; i < records.getSize(); i++){
            if((int)records.getItemAt(i) == num)
                return true;
        }
        return false;
    }

    public void place(int number){
        for(int i = 0; i < playerList.getSize(); i++){
            Player player = (Player) playerList.getItemAt(i);
            player.getBingoGameCard().replace(number);
        }
    }


    public void start(){
        while(!hasWin()){
            printGameBoard();
            int num = getUserInput();
            if(num == 0)
                System.exit(1);
            place(num);
            records.append(num);
        }
        printGameBoard();
        printBingoList();
    }

    private void printBingoList(){
        for(int i = 0; i < playerList.getSize(); i++){
            Player player = (Player) playerList.getItemAt(i);
            if(player.getBingoGameCard().isBingo())
                System.out.printf("Player%s Bingo!\n", player.getName());                
        }
    }

}

class NumberRepeatException extends Exception{
    NumberRepeatException(int num){
        super("The number " + num +" is repeated, please call again!");
    }
}
