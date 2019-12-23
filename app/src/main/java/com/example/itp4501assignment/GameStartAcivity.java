package com.example.itp4501assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class GameStartAcivity extends AppCompatActivity {

    long startTime =System.currentTimeMillis();
    ImageView btn00, btn05, btn50, btn55, selectedView;
    TextView tvError, tvRound;
    String name;
    boolean selected = false;
    int left, right, count;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamestart_main);// get Design layout in layout/gamestart_main
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        btn00 = findViewById(R.id.btn00);// set variable to adapter btn00
        btn05 = findViewById(R.id.btn05);// set variable to adapter btn05
        btn50 = findViewById(R.id.btn50);// set variable to adapter btn50
        btn55 = findViewById(R.id.btn55);
        tvError = findViewById(R.id.textError);// set variable to adapter textError
        tvRound = findViewById(R.id.textRound);// set variable to adapter textRound
        selectedView = findViewById(R.id.imgVewSelected);// set variable to adapter imgVewSelected
        tvRound = findViewById(R.id.textRound);// set variable to adapter textRound
        name=getIntent().getStringExtra("name");// set variable to get intent string name

            if(getIntent().hasExtra("Round"))// check whether new round
                count = getIntent().getIntExtra("Round",0); // not new round get round number

            else
                count=1; // new round set 1

        tvRound.setText("Round: "+count); // setText with Round


    }
    public void onClick(View view) {
        if (view.getId() == R.id.btn00) { // if select the image will display bigger size img
            btn00.setImageResource(R.drawable.gamestart_00sel); // selected img button img change
            btn05.setImageResource(R.drawable.gamestart_05); // default img
            btn50.setImageResource(R.drawable.gamestart_50); // default img
            btn55.setImageResource(R.drawable.gamestart_55); // default img
            left = 0; //set the left value
            right = 0; // set the right value
            selected = true; //set selected
            selectedView.setImageResource(R.drawable.gamestart_cardpin_00);
            // display the selected image

        } else if (view.getId() == R.id.btn05) {
            btn05.setImageResource(R.drawable.gamestart_05sel); // selected img button img change
            btn50.setImageResource(R.drawable.gamestart_50);// default img
            btn55.setImageResource(R.drawable.gamestart_55);// default img
            btn00.setImageResource(R.drawable.gamestart_00);// default img
            left = 0;//set the left value
            right = 5;// set the right value
            selected = true;//set selected
            selectedView.setImageResource(R.drawable.gamestart_cardpin_05);
        } else if (view.getId() == R.id.btn50) {
            btn50.setImageResource(R.drawable.gamestart_50sel); // selected img button img change
            btn55.setImageResource(R.drawable.gamestart_55);// default img
            btn00.setImageResource(R.drawable.gamestart_00);// default img
            btn05.setImageResource(R.drawable.gamestart_05);// default img
            selected = true;//set selected
            left = 5;//set the left value
            right = 0;// set the right value
            selectedView.setImageResource(R.drawable.gamestart_cardpin_50);
        } else if (view.getId() == R.id.btn55) {
            btn55.setImageResource(R.drawable.gamestart_55sel); // selected img button img change
            btn00.setImageResource(R.drawable.gamestart_00);// default img
            btn05.setImageResource(R.drawable.gamestart_05);// default img
            btn50.setImageResource(R.drawable.gamestart_50);// default img
            selected = true;//set selected
            selectedView.setImageResource(R.drawable.gamestart_cardpin_55);
            left = 5;//set the left value
            right = 5;// set the right value
        }
    }
        public void finish() {
            super.finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            // if quit the activity will use this animation
    }

        public void next(View view){
            if (!selected) //check whether select one
                tvError.setText("You should choice one"); //display error message
            else {

                if (count == 1 || count % 2 != 0) { // check round no
                    i = new Intent(this, GameGuessActivity.class);
                    // should choice the guess
                } else {
                    i = new Intent(this, LoadForResult.class);
                    // not choice the guess
                }
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //  get the Intent is not original
                i.putExtra("id", getIntent().getStringExtra("id"));
                //put the Extra value to intent with the id key
                i.putExtra("Left", left);
                //put the Extra value to intent with the Left key
                i.putExtra("Right", right);
                //put the Extra value to intent with the Right key
                i.putExtra("startTime", startTime);
                //put the Extra value to intent with the startTime key
                i.putExtra("Round", count);
                //put the Extra value to intent with the Round key
                i.putExtra("name",name);
                //put the Extra value to intent with the name key
                startActivity(i);
                // start the activity
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                // start the activity with the animation
                finish();
                // quit the activity
            }
        }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
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



