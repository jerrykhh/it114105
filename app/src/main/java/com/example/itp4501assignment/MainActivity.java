package com.example.itp4501assignment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    LinearLayout layoutRegbg;
    AnimationDrawable animationDrawable;
    DateFormat fmtDate = DateFormat.getDateInstance(DateFormat.MEDIUM);
    EditText etDate;
    EditText etName;
    EditText etEmail;
    EditText etPhone;
    Button btnSubmit;



    Calendar myCalendar= Calendar.getInstance(); // Instance the DataTime Picker
    DatePickerDialog.OnDateSetListener d = new
            DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    myCalendar.set(Calendar.YEAR, year); // set the year
                    myCalendar.set(Calendar.MONTH, monthOfYear); // set the Month
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth); // set the Dat
                    updateLabel();
                }
            };
    public static final String SHARED_PREFS = "RegPrefe"; // Instance the SharedPreferences FIle Name



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // set the design layout
        layoutRegbg = findViewById(R.id.bg_reg_layout); // set the adapter of the drawable file
        animationDrawable =(AnimationDrawable) layoutRegbg.getBackground(); // and to the variable
        animationDrawable.setEnterFadeDuration(3000); // set the start anim with Fade in
        animationDrawable.setExitFadeDuration(3000); // and set the end anim with Fade out
        animationDrawable.start(); // start the animation
        SharedPreferences loginPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE); //instance the sharedPreferences
        String userName = loginPreferences.getString("regName",null); // try to get reg Name in sharedPreferences
        if(userName!=null){ // if have the Name, that mean users is registed
            Intent intent = new Intent(this, GameMenuActivity.class); // new the intent of the Game Menu class
            startActivity(intent); // display the GameMenu Class
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left); // and display with this animation
            finish(); // Quit this class
        }

        etDate = findViewById(R.id.regDate); // set the variable to connect the Date TextView
        etName = findViewById(R.id.regName); // set the variable to connect the Name TextView
        etEmail = findViewById(R.id.regEmail); // set the variable to connect the Email TextView
        etPhone = findViewById(R.id.regPhone); // set the variable to connect the Phone TextView
        btnSubmit = findViewById(R.id.btnSubmit); // set the variable to connect the submit Button
        etDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { // set the Date Picker to set the Date Format
                new DatePickerDialog(MainActivity.this, d,
                        myCalendar.get(Calendar.YEAR), // set the Year
                        myCalendar.get(Calendar.MONTH), // set the month
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show(); //set the day
                updateLabel();  // update the TextView of selected Date
            }
        });



    }
    private void updateLabel() {
        etDate.setText(fmtDate.format(myCalendar.getTime()));
    } // update the editText of the selected Date value

    public void onClick(View view){
        if(TextUtils.isEmpty(etName.getText()))  // check the Name editText whether null
            etName.setError( "Name is required!" ); // display the require message
        else if(TextUtils.isEmpty(etDate.getText())) // check the Date whether null
            etDate.setError("Date is required!"); // display the require message
        else if(TextUtils.isEmpty(etPhone.getText())) // check the phone whether null
            etPhone.setError("Phone is required!"); //display the require message
        else if (TextUtils.isEmpty(etEmail.getText())) // check the email whether null
            etEmail.setError("Email is required!");// display the require message
        else {
            //Not the editText is null will instance the SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("regName", etName.getText().toString());
            //put the Name editText value to SharedPreferences with "regName" key
            editor.putString("regDate", etDate.getText().toString());
            //put the Date editText value to SharedPreferences with "regDate" key
            editor.putString("regEmail", etEmail.getText().toString());
            //put the Email editText value to SharedPreferences with "regEmail" key
            editor.putString("regPhone", etPhone.getText().toString());
            //put the regPhone editText value to SharedPreferences with "regPhone" key
            editor.commit(); // commit the sharedPreferences
            createTable(); // when the registed the game will create the database
            Intent intent = new Intent(this, GameMenuActivity.class);
            // set the intent to connect the GameMenuActivity class
            startActivity(intent);
            // Show the GameMenuActivity Class
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            // show the class with the animation
            finish(); // quit the class

        }

    }

    public void createTable(){
        try{
            SQLiteDatabase db; // set the variable of Database
            db=SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.CREATE_IF_NECESSARY); //create the Database will the patch
            String sql = "DROP TABLE if exists GamesLog;";  //Drop the Table SQL avoid exception occured
            db.execSQL(sql);  // run SQL
            sql = "CREATE TABLE GamesLog( ID INTEGER PRIMARY KEY  AUTOINCREMENT, gameDate DATETIME DEFAULT CURRENT_DATE,"+
                    "gameTime text, opponentName text, winOrLost INT); ";// CREATE Table SQL
            db.execSQL(sql); // run SQL
            db.close(); // close the database
        } catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); //show the error message with Toast
        }


}

}
