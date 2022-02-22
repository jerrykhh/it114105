package com.example.itp4501assignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GameMenuActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamemenu_main);// get Design layout in layout/gamemenu_main
    }

    public void onClick(View view){
        if(view.getId()==R.id.btnCancel){//if click cancel button
            finish(); // quit the game
        }else if(view.getId()==R.id.btnUpdateInf){//if click btnUpdateInf button
            intent = new Intent(this, UpdateInformation.class);
            // intent of UpdateInformation class
            startActivity(intent); //start Activity
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //display with the animation

        }else if(view.getId()==R.id.btnStart){//if click btnStart button
            intent = new Intent(this, GameLoadingActivity.class);
            // intent of GameLoadingActivity class
            startActivity(intent);//start Activity
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //display with the animation

        }else if(view.getId() == R.id.btnGraphic){//if click btnGraphic button
            intent = new Intent(this, PieChartActivity.class);
            // intent of GameLoadingActivity class
            startActivity(intent);//start Activity
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //display with the animation
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        //display with the animation when quit this activity
    }
}
