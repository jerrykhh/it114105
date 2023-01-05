package com.game.tictacteo.engine;

import android.util.Log;

public class GameAI {

    static final byte player = 2;
    static final byte opponent = 1;

    private static Boolean isMovesLeft(byte board[][]) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                if (board[i][j] == 0)
                    return true;
        return false;
    }

    private static int evaluate(byte[][] board) {
        // Checking for Rows is player or opponent victory.
        for (int row = 0; row < board.length; row++) {
            if (board[row][0] == board[row][1] &&
                    board[row][1] == board[row][2]) {
                if (board[row][0] == player)
                    return +10;
                else if (board[row][0] == opponent)
                    return -10;
            }
        }


        for (int col = 0; col < board[0].length; col++) {
            if (board[0][col] == board[1][col] &&
                    board[1][col] == board[2][col]) {
                if (board[0][col] == player)
                    return +10;

                else if (board[0][col] == opponent)
                    return -10;
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == player)
                return +10;
            else if (board[0][0] == opponent)
                return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == player)
                return +10;
            else if (board[0][2] == opponent)
                return -10;
        }

        // Else if none of them have won then return 0
        return 0;
    }

    private static int minimax(byte board[][], int depth, Boolean isMax) {
        int score = evaluate(board);

        // Evaluated score if Maximizer has won the game
        if (score == 10)
            return score;

        if (score == -10)
            return score;

        // no winner then it is a tie if false
        if (isMovesLeft(board) == false)
            return 0;

        if (isMax) {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    // Check cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = player;

                        // calculate minimax recursively and choose
                        best = Math.max(best, minimax(board, depth + 1, !isMax));

                        // Undo move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }else {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    // Check cell is empty
                    if (board[i][j] == 0) {
                        // Make the move
                        board[i][j] = opponent;

                        // calculate minimax recursively and choose
                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));

                        // Undo move
                        board[i][j] = 0;
                    }
                }
            }
            return best;
        }
    }

    public static int[] findBestMove(byte board[][]) {
        int bestVal = -1000;
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;

        // evaluate minimax func
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // Check cell is empty
                if (board[i][j] == 0) {
                    // Make the move
                    board[i][j] = player;

                    // compute evaluation function
                    int moveVal = minimax(board, 0, false);

                    // Undo the move
                    board[i][j] = 0;

                    // If the value of the current move is higher then best value, then update
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

}
