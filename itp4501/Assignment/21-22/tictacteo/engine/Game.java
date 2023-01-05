package com.game.tictacteo.engine;

import android.content.Context;
import android.util.Log;

import com.game.tictacteo.localDB.GameLogDB;
import com.game.tictacteo.model.GameLog;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Game {

    private Timestamp startTime;
    private Timestamp endTime;
    private GameBoard board;
    private Context ctx;


    public Game(Context ctx) {
        startTime = new Timestamp(System.currentTimeMillis());
        board = new GameBoard();
        this.ctx = ctx;
    }

    // User move
    public GameStatus player1Place(int row, int col) throws GameBoardPlacedException {

        GameStatus gs = place(row, col, 1);
        checkGameStatus(gs); // check status if won not need AI move
        if (gs.status == GameBoard.Status.CONTINUE) {
            gs = aiPlayerPlace();
            checkGameStatus(gs); // check status
        }
        return gs;
    }

    private GameStatus place(int row, int col, int player) throws GameBoardPlacedException {

        if (player == 1)
            return this.board.placePlayer1(row, col);
        else if (player == 2)
            return this.board.placePlayer2(row, col);
        return null;
    }

    private void checkGameStatus(GameStatus gs) {
        // check status if Player1 or Player2 win need to insert the log to db
        if (gs.status != GameBoard.Status.CONTINUE) {
            endTime = new Timestamp(System.currentTimeMillis());
            int duration = (int) ((endTime.getTime() - startTime.getTime()) / 1000);

            int winingStatus = ((gs.status == GameBoard.Status.DRAW) ? 2 : (gs.status == GameBoard.Status.PLAYER2_WIN) ? 0 : 1);
            GameLogDB.getInstance(this.ctx).addLog(new GameLog(new Date(endTime.getTime()), new Time(endTime.getTime()), duration, winingStatus));
        }
    }

    // AI move
    private GameStatus aiPlayerPlace() throws GameBoardPlacedException {
        Log.i("game-OBJ-AI", "aiPLayerPlace");
        int[] move = GameAI.findBestMove(board.getBoard());
        return place(move[0], move[1], 2);

    }


}
