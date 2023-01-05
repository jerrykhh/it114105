package com.game.tictacteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStartGame;
    Button btnRanking;
    Button btnRecords;
    Button btnClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartGame = (Button) findViewById(R.id.btnPlay);
        btnRanking = (Button) findViewById(R.id.btnRanking);
        btnRecords = (Button) findViewById(R.id.btnRec);
        btnClose = (Button) findViewById(R.id.btnClose);

        // Start game
        btnStartGame.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        // Game Ranking
        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });

        // Your Records
        btnRecords.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this, RecordActivity.class);
                startActivity(intent);

            }
        });

        // Close
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("click");
                finish();
            }
        });
    }
}