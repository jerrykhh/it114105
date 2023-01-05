package com.game.tictacteo.engine;

import android.util.Log;

public class GameBoard {

    private final static int ROW = 3; // Game Board ROW
    private final static int COL = 3; // Game Board COL

    private byte[][] board;
    private short step;
    private short winner;   // Winner

    public enum Status {
        CONTINUE,
        PLAYER1_WIN,
        PLAYER2_WIN,
        DRAW
    }

    //  0 0 0
    //  0 0 0
    //  0 0 0

    GameBoard() {
        board = new byte[ROW][COL];
        step = 0;
        winner = -1;
    }

    public byte[][] getBoard() {
        return board;
    }

    public GameStatus placePlayer1(int row, int col) throws GameBoardPlacedException {
        return place(row, col, 1);
    }

    public GameStatus placePlayer2(int row, int col) throws  GameBoardPlacedException{
        return place(row, col, 2);
    }


    private GameStatus place(int row, int col, int player) throws GameBoardPlacedException {
        if(board[row][col] != 0)
            throw new GameBoardPlacedException("The position is placed: " + row + " " + col);
        board[row][col] = (byte) player;
        step++;     // count step for game
        return genGameStatus();
    }

    private GameStatus genGameStatus(){

        // return current game board status
        // step == board.length mean all is placed
        if(step == board[0].length * board.length)
            return new GameStatus(Status.DRAW, this.board);

        // check has winner?
        if(winner == -1 && !checkWin())
            return new GameStatus(Status.CONTINUE, this.board);
        else
            return new GameStatus((this.winner == 1) ? Status.PLAYER1_WIN: Status.PLAYER2_WIN, this.board);

    }

    private boolean checkWin(){
        return (checkRows() || checkColumns() || checkDiagonals());
    }


    private boolean checkRows() {
        for (int i = 0; i < board.length; i++) {
            int count = 1;
            for (int j = 1; j < board[i].length; j++) {
                // compare other col is same?
                if (board[i][0] != 0 && board[i][0] == board[i][j])
                    count++;
                else
                    break;
            }
            if (count == board[i].length){
                this.winner = board[i][0];
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int i = 0; i < board[0].length; i++) {
            int count = 1;
            for (int j = 1; j < board.length; j++) {
                // compare other col is same?
                if (board[0][i] != 0 && board[0][i] == board[j][i])
                    count++;
                else
                    break;
            }
            if (count == board.length) {
                this.winner = board[0][i];
                return true;
            }

        }

        return false;
    }

    // checkDiagonals
    private boolean checkDiagonals() {
        int countX = 1; // for count left-top to right-bottom
        int countY = 1; // for count right-top to left-bottom
        for (int j = 1; j < board.length; j++) {
            // compare other col is same?
            if (board[0][0] != 0 && board[0][0] == board[j][j])
                countX++;
            if (board[0][board[0].length - 1] != 0 && board[0][board[0].length - 1] == board[j][board[0].length - 1 - j])
                countY++;
        }


        if (countX == board.length) {
            this.winner = board[0][0];
            return true;
        }

        if (countY == board.length) {
            this.winner = board[0][board[0].length-1];
            return true;
        }

        return false;

    }


}

