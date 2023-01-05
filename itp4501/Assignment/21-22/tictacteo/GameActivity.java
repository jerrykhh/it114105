package com.game.tictacteo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.game.tictacteo.engine.Game;
import com.game.tictacteo.engine.GameBoard;
import com.game.tictacteo.engine.GameBoardPlacedException;
import com.game.tictacteo.engine.GameStatus;

import java.util.HashMap;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private HashMap<Integer, LocateButton> gameBoardMap;
    private Game game;
    private ImageView imGameMes;
    private final static byte USER_PLAYER = 1;
    private final static byte AI_PLAYER = 2;
    //private ArrayList<Integer> lockedButton;
    private ImageView imDisPlayer;
    private ImageView imDisAI;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        imGameMes = (ImageView) findViewById(R.id.txtGameMes);
        imDisPlayer = (ImageView) findViewById(R.id.imDisplayPlayer);
        imDisAI = (ImageView) findViewById(R.id.imDisplayAI);
        btnContinue = (Button) findViewById(R.id.btnContinue);
        btnContinue.setVisibility(View.GONE);

        // Close button
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });

        // Button Map to reduce the runtime
        gameBoardMap = new HashMap<Integer, LocateButton>();

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "btn_"+i+"_"+j;
                int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                Button button = (Button) findViewById(resourceID);
                button.setOnClickListener(this);
                Log.i("gameAc-onCreate", "refesh");
                gameBoardMap.put(resourceID, new LocateButton(button, new int[]{i, j}));
            }
        }
        Log.i("gameAc", "start");

        game = new Game(GameActivity.this);
        //lockedButton = new ArrayList<Integer>();
    }

    // refesh the game board
    public void refesh(byte[][] board){
        Log.i("gameAc", "refesh");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                String mapKey = "btn_"+i+"_"+j;
                int resourceID = getResources().getIdentifier(mapKey, "id", getPackageName());

                // Get button from hashmap
                Button button = gameBoardMap.get(resourceID).button;

                // set button image and disable clicked button
                if(board[i][j] == USER_PLAYER){
                    button.setBackgroundResource(R.drawable.ic_o);
                    button.setClickable(false);
                }else if (board[i][j] == AI_PLAYER){
                    button.setBackgroundResource(R.drawable.ic_x);
                    button.setClickable(false);
                }else{
                    button.setBackgroundResource(R.drawable.game_board_pos);
                    button.setClickable(true);
                }
            }
        }
    }

    // disable all button
    public void lockAllButton(){
        for(LocateButton lb: gameBoardMap.values())
            lb.button.setClickable(false);
    }

//    public void unlockAllButton(){
//        for(Integer key: gameBoardMap.keySet()){
//            Button button = gameBoardMap.get(key).button;
//            if(lockedButton.contains(key))
//                button.setClickable(false);
//            else
//                button.setClickable(true);
//
//        }
//    }


    @Override
    public void onClick(View view) {

            Log.i("gameAc", view.getId() +"");
            if (gameBoardMap.containsKey(view.getId())) {
                LocateButton lb = gameBoardMap.get(view.getId());

                // Player place
                lockAllButton();

                // set clicked button UI setting
                lb.button.setBackgroundResource(R.drawable.ic_o);
                imGameMes.setImageResource(R.drawable.game_mes_2);
                imDisPlayer.setBackgroundResource(0);
                imDisAI.setBackgroundResource(R.drawable.btnranking);

                //lockedButton.add(view.getId());

                try {

                    // Place the index
                    GameStatus gs = game.player1Place(lb.locate[0], lb.locate[1]);
                    this.refesh(gs.board);

                    imGameMes.setImageResource(R.drawable.game_mes_1);
                    imDisAI.setBackgroundResource(0);

                    // Check returned game statue and update the UI
                    if(gs.status == GameBoard.Status.CONTINUE) {
                        imDisPlayer.setBackgroundResource(R.drawable.btnranking);
                        //unlockAllButton();
                    }else{

                        if (gs.status == GameBoard.Status.DRAW){
                            imDisPlayer.setBackgroundResource(R.drawable.btnranking);
                            imGameMes.setImageResource(R.drawable.game_dis_draw);
                        }else if (gs.status == GameBoard.Status.PLAYER1_WIN){
                            imGameMes.setImageResource(R.drawable.game_dis_win);
                        }else if(gs.status == GameBoard.Status.PLAYER2_WIN) {
                            imDisPlayer.setBackgroundResource(0);
                            imDisAI.setBackgroundResource(R.drawable.btnranking);
                            imGameMes.setImageResource(R.drawable.game_dis_lose);
                        }
                        btnContinue.setVisibility(View.VISIBLE);
                    }

                } catch (GameBoardPlacedException e) {
                    e.printStackTrace();
                }
            }

    }
}

class LocateButton {
    Button button;
    int[] locate; // row, col index

    LocateButton(Button button, int[]locate){
        this.button = button;
        this.locate = locate;
    }
}