package com.example.itp4501assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameGuessActivity  extends AppCompatActivity {


    int guess;
    String name;
    TextView tvRound;
    Intent i;
    LinearLayout lyQuestion, lyZero, lyFive, lyTen, lyfift, lyTwe;
    LinearLayout[] layouts;
    Animation animation;
    Handler handler = new Handler();





    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamegues_main);// get Design layout in layout/gamegues_main
        animation= AnimationUtils.loadAnimation(this,R.anim.bounce); // instance the animation
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        lyQuestion=findViewById(R.id.lyQuestion);// set variable to adapter lyQuestion
        lyZero=findViewById(R.id.lyZero);// set variable to adapter lyZero
        lyFive=findViewById(R.id.lyFive);// set variable to adapter lyFive
        lyTen=findViewById(R.id.lyTen);// set variable to adapter lyTen
        lyfift=findViewById(R.id.lyfift);// set variable to adapter lyfift
        lyTwe=findViewById(R.id.lyTwe);// set variable to adapter lyTwe
        i = new Intent(this, LoadForResult.class); //instance the Intent
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //  get the Intent is not original
        tvRound = findViewById(R.id.textRound);// set variable to adapter textRound
        tvRound.setText("Round: "+getIntent().getIntExtra("Round",0));
        //set the round text
        name=getIntent().getStringExtra("name"); //get the Intent value and store in name variable
    }
    public void onWindowFocusChanged(boolean hasFocus) { //when start the activity
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            lyZero.startAnimation(animation); //layout lyZero start the animation
            lyFive.startAnimation(animation);//layout lyFive start the animation
            lyTen.startAnimation(animation);//layout lyTen start the animation
            lyfift.startAnimation(animation);//layout lyfift start the animation
            lyTwe.startAnimation(animation);//layout lyTwe start the animation
            lyQuestion.startAnimation(animation);//layout lyQuestion start the animation
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        }
    }


    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);// Confirm get the Intent and set the Intent is not original
    }

    public void onClick(View view){
        if(view.getId()==R.id.lyZero || view.getId()==R.id.txtZero){ //check onClick value
            guess = 0;  //store the onclick value to variable
        }else if (view.getId()==R.id.lyFive || view.getId()==R.id.txtFive){//check onClick value
            guess = 5;  //store the onclick value to variable
        }else if (view.getId()==R.id.lyTen || view.getId()==R.id.txtTen){//check onClick value
            guess = 10;  //store the onclick value to variable
        }else if (view.getId()==R.id.lyfift || view.getId()==R.id.txtfift){//check onClick value
            guess =15;  //store the onclick value to variable
        }else if(view.getId()==R.id.lyTwe || view.getId()==R.id.txtTwe){//check onClick value
            guess =20;  //store the onclick value to variable
        }

        i.putExtra("id", getIntent().getStringExtra("id"));//put the Extra value to intent with the id key
        i.putExtra("Left", getIntent().getIntExtra("Left",0));//put the Extra value to intent with the Left key
        i.putExtra("Right", getIntent().getIntExtra("Right",0));//put the Extra value to intent with the Right key
        i.putExtra("startTime",getIntent().getLongExtra("startTime",0));//put the Extra value to intent with the startTime key
        i.putExtra("name",name);//put the Extra value to intent with the name key
        i.putExtra("Round",getIntent().getIntExtra("Round",0));//put the Extra value to intent with the Round key
        i.putExtra("guess",guess);//put the Extra value to intent with the guess key
        startActivity(i); //start intent
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        // display with animation
        finish(); //quit the game
    }
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        //display with aniamtion
    }
    @Override
    public void onBackPressed() {//when not finish the game will display the alert message
        new AlertDialog.Builder(this) // set the alert message
                .setIcon(android.R.drawable.ic_dialog_alert) // display the wronging icon
                .setTitle("Closing Game") // set the alert message title
                .setMessage("Are you sure you want to quit this game?\nYou may Lost this game")
                // set the message description
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//Yes button
                        insertDate(); // onClick the yes button will call the insertDate method
                        finish(); // quit the activity
                    }

                })
                .setNegativeButton("No", null) // NO button
                .show(); // show the alert message
    }

    public void insertDate(){
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.OPEN_READWRITE);//open database
            String sql = "INSERT INTO GamesLog(gameTime, opponentName, winOrLost) values"
                    + "(?, ?, ?)"; // insert sql with the parameter

            db.execSQL(sql, new String[]{0 + "", name, 0 + ""}); // execute the SQL with the ags
            db.close(); // database close
        }catch (SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); //show Error message

        }

    }


}