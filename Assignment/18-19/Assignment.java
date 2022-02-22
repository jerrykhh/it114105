/*Name:Kwok Ho Hin
StudenID: 180160427
Class: IT114105-1B
Description of purpose: 6x6 Reversi game in console mode. The letter 1 mean black, the letter 2 mean white and the letter 0 mean empty space. 
*/

import java.util.Scanner;

public class Assignment {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] reversi = new int[6][6];/*Reversi Array*/
        boolean End = false; /*End is false*/
        int inputX, inputY, countWhite = 0, countBlack = 0, player = 1, countPlay = 1;/*default player is 1, start the Countturn is 1, inputX and inputY for the Scanner can position*/
        reversi[2][2] = 1;
        reversi[3][3] = 1;
        reversi[3][2] = 2;
        reversi[2][3] = 2;
        endGamebk:
        do {
            boolean inputEnd = false; /*reset inputEnd is false, player need input the values*/
            System.out.println("    0 1 2 3 4 5\n   -------------");
            for (int count = 0; count < 6; count++) {                /*print the gameboard*/
                System.out.print(count + " | ");
                for (int i = 0; i <= 5; i++) {
                    System.out.print(reversi[count][i] + " ");
                }
                System.out.print("\n");
            }

            if (countPlay % 2 == 0) {
                player = 2;
            } else {
                player = 1;
            }                                              /*calculator the player odd number of turn is 1, even number of turn is 2*/
            truebk:
            for (int count = 0; count < 6; count++) {
                for (int i = 0; i <= 5; i++) {
                    if (reversi[count][i] == 0 && checkMove(reversi, count, i, player, true)) {
                        break truebk;                      /*find have or have not empty can put, if find it break the loop*/
                    } else if (count == 5 && i == 5 && !checkMove(reversi, 5, 5, 2, true) && !checkMove(reversi, 5, 5, 1, true)) {
                        break endGamebk;                     /*not empty space put so finish the game*/
                    }
                }
            }

            do {
                System.out.print("Please enter the position of '" + player + "' :");
                inputX = input.nextInt();
                inputY = input.nextInt();
                if (inputX > 5 || inputX < 0 || inputY > 5 || inputY < 0) {
                    /*Check input value*/
                    System.out.println("Error - input numbers should be 0 to 5!");
                }else if(reversi[inputX][inputY]!=0){      /*check input position whether empty*/
                    System.out.println("Error - input cell is not empty");
                } else if (checkMove(reversi, inputX, inputY, player, false)) {
                    reversi[inputX][inputY] = player;
                    int countInput = 0;
                    for (int i = 0; i < 6; i++) {                                /*Check can a input next turn*/
                        for (int count = 0; count < 6; count++) {
                            if ((countPlay + 1) % 2 == 0 && checkMove(reversi, i, count, 2, true) == false) {
                                countInput++;
                            } else if ((countPlay + 1) % 2 != 0 && checkMove(reversi, i, count, 1, true) == false) {
                                countInput++;
                            }
                            if (countInput > 35) {                           /*Check next turn player whether can put */
                                countPlay++; /*next turn*/
                            }
                        }
                    }
                    countPlay++;     /*next turn*/
                    inputEnd = true;  /*End of input*/
                } else {
                    System.out.println("Error - invalid move");
                }

            } while (inputEnd == false);
        } while (End == false);
        for (int i = 0; i < 6; i++) {
            /*Counter*/
            for (int count = 0; count < 6; count++) {
                if (reversi[i][count] == 1) {
                    countBlack++;   
                } else if (reversi[i][count] == 2) {  /*cannot use 'else' because the reversi game not finish in use all table may include a letter'0'*/
                    countWhite++;
                }
            }
        }
        System.out.print("Game Finishes.\n\t'1' - " + countBlack + "\n\t'2' - " + countWhite);
        if (countWhite < countBlack) {
            System.out.print("\nBlack wins.");
        } else {
            System.out.print("\nWhite wins.");
        }
    }
	/*Check the position and faceing up, if the inputtest is true that mean the method onlu check the position but not faceing up*/
    public static boolean checkMove(int reversi[][], int inputX, int inputY, int player, boolean inputtest) {
        boolean[] check = new boolean[8];
        if (reversi[inputX][inputY] == 0) {    /*only empty space can put*/
            for (int i = 1; i <= inputX; i++) {  /*checkTop*/
                if (inputX - i < 0 || reversi[inputX - 1][inputY] == 0) { /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX - 1][inputY] != player && reversi[inputX - i][inputY] == player && reversi[inputX - i][inputY] != 0) { /*on top of not a same  and find do you have*/
                    check[0] = true;
                    if (inputtest == true) {                      /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX - count][inputY] = player;
                    }
                }
            }
            for (int i = 1; i <= 5 - inputX; i++) {/*checkdown*/
                if (inputX + i > 5 || reversi[inputX + 1][inputY] == 0) {/*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX + 1][inputY] != player && reversi[inputX + i][inputY] == player && reversi[inputX + i][inputY] != 0) {
                    check[1] = true;
                    if (inputtest == true) {                      /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX + count][inputY] = player;
                    }
                }
            }
            for (int i = 1; i <= inputY; i++) {/*checkleft*/
                if (inputY - i < 0 || reversi[inputX][inputY - 1] == 0) {        /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX][inputY - 1] != player && reversi[inputX][inputY - i] == player && reversi[inputX][inputY - i] != 0) {
                    check[2] = true;
                    if (inputtest == true)                         {/*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX][inputY - count] = player;
                    }
                }
            }
            for (int i = 1; i <= 5 - inputY; i++) {/*checkRight*/
                if (inputY + i > 5 || reversi[inputX][inputY + 1] == 0) {       /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX][inputY + 1] != player && reversi[inputX][inputY + i] == player && reversi[inputX][inputY + i] != 0) {
                    check[3] = true;
                    if (inputtest == true) {               /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX][inputY + count] = player;
                    }
                }
            }
            for (int i = 1; i <= 5; i++) {/*checkRightTopL*/
                if ((inputX - i < 0 || inputY + i > 5) || reversi[inputX - i][inputY + i] == 0) {       /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX - 1][inputY + 1] != player && reversi[inputX - i][inputY + i] == player && reversi[inputX - i][inputY + i] != 0) {
                    check[4] = true;
                    if (inputtest == true) {                                /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX - count][inputY + count] = player;
                    }
                }
            }
            for (int i = 1; i <= 5; i++) {/*RightDownL*/
                if ((inputX + i > 5 || inputY + i > 5) || reversi[inputX + i][inputY + i] == 0) {      /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX + 1][inputY + 1] != player && reversi[inputX + i][inputY + i] == player && reversi[inputX + i][inputY + i] != 0) {
                    check[5] = true;
                    if (inputtest == true) {              /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX + count][inputY + count] = player;
                    }
                }
            }
            for (int i = 1; i <= 5; i++) {/*checkLeftTopL*/
                if ((inputX - i < 0 || inputY - i < 0) || reversi[inputX - i][inputY - i] == 0) {   /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX - 1][inputY - 1] != player && reversi[inputX - i][inputY - i] == player && reversi[inputX - i][inputY - i] != 0) {
                    check[6] = true;
                    if (inputtest == true) {               /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX - count][inputY - count] = player;
                    }
                }
            }
            for (int i = 1; i <= 5; i++) {/*checkLeftDownL*/
                if ((inputX + i > 5 || inputY - i < 0) || reversi[inputX + i][inputY - i] == 0) {       /*if arrary expection -> break*/
                    break;
                } else if (reversi[inputX + 1][inputY - 1] != player && reversi[inputX + i][inputY - i] == player && reversi[inputX + i][inputY - i] != 0) {
                    check[7] = true;
                    if (inputtest == true) {                    /*Check test or not, if this for the test not need facing up */
                        break;
                    }
                    for (int count = 1; count <= i; count++) {
                        reversi[inputX + count][inputY - count] = player;   /*if */
                    }
                }
            }
        }
        return check[0] || check[1] || check[2] || check[3] || check[4] || check[5] || check[6] || check[7];
    }
}
