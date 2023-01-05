package com.game.tictacteo.engine;

public class GameStatus {

    public GameBoard.Status status;
    public byte[][] board;

    public GameStatus(GameBoard.Status status, byte[][] board){
        this.status = status;
        this.board = board;
    }
}
