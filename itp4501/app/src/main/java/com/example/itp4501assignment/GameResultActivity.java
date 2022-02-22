package com.example.itp4501assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameResultActivity extends AppCompatActivity {


    Intent i;
    TextView txtOpName, txtName, txtWinOrLost, txtGuess;
    ImageView imgOpLeft, imgOpRight, imgLeft, imgRight;
    Button btnResultContine, btnResultQuit;
    public static final String SHARED_PREFS = "RegPrefe";
    SharedPreferences sharedPreferences;

    int opLeft,opRight,opGuess,left,right,guess,round;
    long startTime;
    long endTime =System.currentTimeMillis();
    String name, second, id;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameresult_main);// get Design layout in layout/gameresult_main
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        opLeft=getIntent().getExtras().getInt("opLeft");// set variable to get intent string opLeft
        opRight=getIntent().getExtras().getInt("opRight");// set variable to get intent string opRight
        opGuess=getIntent().getExtras().getInt("opGuess");// set variable to get intent string opGuess
        name =getIntent().getExtras().getString("name");// set variable to get intent string name
        left= getIntent().getExtras().getInt("Left");// set variable to get intent string Left
        right=getIntent().getExtras().getInt("Right");// set variable to get intent string Right
        guess=getIntent().getExtras().getInt("guess");// set variable to get intent string guess
        round = getIntent().getExtras().getInt("Round");// set variable to get intent string Round
        id=getIntent().getExtras().getString("id");// set variable to get intent string id
        startTime = getIntent().getExtras().getLong("startTime");// set variable to get intent string startTime
        txtOpName = findViewById(R.id.txtOpName);// set variable to adapter txtOpName
        txtName = findViewById(R.id.txtName);// set variable to adapter txtName
        txtWinOrLost = findViewById(R.id.txtWinOrLose);// set variable to adapter txtWinOrLose
        imgOpLeft = findViewById(R.id.imgOpLeft);// set variable to adapter imgOpLeft
        imgOpRight = findViewById(R.id.imgOpRight);// set variable to adapter imgOpRight
        imgLeft = findViewById(R.id.imgLeft);// set variable to adapter imgLeft
        imgRight = findViewById(R.id.imgRight);// set variable to adapter imgRight
        txtGuess = findViewById(R.id.txtGuess);// set variable to adapter txtGuess
        txtOpName.setText(name); //set Text of the opName
        txtName.setText(sharedPreferences.getString("regName",""));// set Text of owner name
        btnResultContine = findViewById(R.id.btnResultContine);// set variable to adapter btnResultContine
        btnResultQuit = findViewById(R.id.btnResultQuit);// set variable to adapter btnResultQuit


        if(left==0) //display left 0  image
            imgLeft.setImageResource(R.drawable.left);
        else if(left==5)//display left 5  image
            imgLeft.setImageResource(R.drawable.left_5);

        if(right==0)//display right 0  image
            imgRight.setImageResource(R.drawable.right);
        else if(right==5)//display right 5  image
            imgRight.setImageResource(R.drawable.right_5);

        if(opLeft==0)//display opponents left 0 image
            imgOpLeft.setImageResource(R.drawable.opright);
        else if(opLeft==5)//display opponents left 5 image
            imgOpLeft.setImageResource(R.drawable.opright_5);

        if(opRight==0)//display opponents right 0 image
            imgOpRight.setImageResource(R.drawable.opleft);
        else if(opRight==5)//display opponents right 5 image
            imgOpRight.setImageResource(R.drawable.opleft_5);


        if(round==1 || round%2!=0) { // check use who guess
            txtGuess.setText("Your Guess: "+guess); // set txtGuess value
            if (opLeft + opRight + left + right == guess){ // if win
                txtWinOrLost.setText("You Win"); //set the Win Text
                btnResultContine.setText("Next Game"); //and display the button text
                insertData(true);   // call the insert method
            }else{
                txtWinOrLost.setText("Deuce Round"); //print the Text of Deuce when Deuce Round
            }
        }else{
            txtGuess.setText("Opponent Guess: "+opGuess);// set txtGuess value of Opponent guess
            if(opLeft+opRight+left+right==opGuess) { // if Opponent win
                txtWinOrLost.setText("You Lose"); //set the Lose Text
                btnResultContine.setText("Next Game"); //and display the button text
                insertData(false);// call the insert method
            }else{
                txtWinOrLost.setText("Deuce Round");//print the Text of Deuce when Deuce Round
            }
        }
    }
    public void insertData(boolean winOrLost){ // insert data win or lost
        int changeWinLostVal;
        if(winOrLost) //check the value is lost or win and check to the integer
            changeWinLostVal = 1;  // if win
        else
            changeWinLostVal = 0; // if lost
        try {
            second = ((endTime - startTime) / 1000) + ""; // finsih the game time
            db = SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.OPEN_READWRITE); //open database
            String sql = "INSERT INTO GamesLog(gameTime, opponentName, winOrLost) values"
                    + "(?, ?, ?)"; // insert game result SQL with the parameter

            db.execSQL(sql, new String[]{second, name, changeWinLostVal + ""}); // Run the SQL
            db.close(); // database close
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //Show the Error Message
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);// Confirm get the Intent and set the Intent is not original
        setIntent(intent);
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


    public void onClick(View view){
        if(view.getId()==R.id.btnResultContine) {
            if (txtWinOrLost.getText().toString() == "You Win" || txtWinOrLost.getText().toString() == "You Lose") {
                //check if Lose or WIn
                i = new Intent(this, GameLoadingActivity.class);
                //will have the button to play next game
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            } else if (txtWinOrLost.getText().toString() == "Deuce Round") {
                //check the game is not win or lost
                i = new Intent(this, GameStartAcivity.class);
                //will have the button to continue the game
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //  get the Intent is not original
                i.putExtra("Round", round + 1);
                //put the Extra value to intent with the Round key
                i.putExtra("name", name);
                //put the Extra value to intent with the name key
                i.putExtra("id", id);
                //put the Extra value to intent with the id key
            }
            startActivity(i);
            //start Activity with animation
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();// quit the game
        }else if(view.getId()==R.id.btnResultQuit &&txtWinOrLost.getText().toString() == "Deuce Round"){
            onBackPressed();
        }else if(view.getId()==R.id.btnResultQuit){
            // check round is not finish
                finish();
        }
    }
    @Override
    public void onBackPressed() {
        if(txtWinOrLost.getText().toString()=="Deuce Round") { // if Game is not finish and the user want to quit the game
            new AlertDialog.Builder(this) // set alert message
                    .setIcon(android.R.drawable.ic_dialog_alert) // set the alert icon
                    .setTitle("Closing Game") // set the Title in alert message
                    .setMessage("Are you sure you want to quit this game?\nYou may Lost this game") //set the message
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { // when click yes button
                            insertDate(); // call the insert method
                            finish(); // quit the activity
                        }

                    })
                    .setNegativeButton("No", null) // No button
                    .show(); // show message
        }else {
            finish(); //quit the activity
        }
    }

    public void insertDate(){
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.OPEN_READWRITE);//open database
            String sql = "INSERT INTO GamesLog(gameTime, opponentName, winOrLost) values"
                    + "(?, ?, ?)";// set the insert sql with parameter

            db.execSQL(sql, new String[]{0 + "", name, 0 + ""});//execute SQL with args
            db.close(); //database close
        }catch (SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            //Show error message
        }

    }
}

