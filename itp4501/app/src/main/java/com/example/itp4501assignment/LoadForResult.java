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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadForResult  extends AppCompatActivity {

    FetchPagetask task=null;
    String id, name;
    int opLeft, opRight, opGuess;
    Intent i;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadforresult_main);// get Design layout in layout/loadforresult_main
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        id = getIntent().getStringExtra("id"); // set the variable to store the Intent value
        name=getIntent().getStringExtra("name");// set the variable to store the Intent value
        i =new Intent(LoadForResult.this, GameResultActivity.class);// instance the Intent
        i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP); // NOT GET THE orgial Intent
        if(task ==null ||
                task.getStatus().equals(AsyncTask.Status.FINISHED)){ // check task whether null
            task = new FetchPagetask(); // instance the FetchPagetask Class
            task.execute("https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/"+id);
            //send the API url to FetchPagetask class

        }
    }
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Confirm get the Intent and set the Intent is not original
    }
    @Override
    public void onBackPressed() { //when not finish the game will display the alert message
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Game") // alert message Title
                .setMessage("Are you sure you want to quit this game?\nYou may Lost this game") // alert message Description
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { // Yes button
                        insertDate(); //onClick the Yes button will call the insertDate method
                        finish(); // end the activity
                    }

                })
                .setNegativeButton("No", null) // NO button
                .show(); // show alert message
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

    public void insertDate(){
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase("/data/data/com.example.itp4501assignment/GamesLog",
                    null, SQLiteDatabase.OPEN_READWRITE); //open Database
            String sql = "INSERT INTO GamesLog(gameTime, opponentName, winOrLost) values"
                    + "(?, ?, ?)"; //insert the lose sql with parameter

            db.execSQL(sql, new String[]{0 + "", name, 0 + ""});
            // execute the SQL and include the opp name
            db.close();//database close
        }catch (SQLiteException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show(); //show error message

        }

    }


    private class FetchPagetask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... values) {
            InputStream inputStream = null;
            String result = "";
            URL url = null;

            try {
                url = new URL(values[0]);// get the url in LoadForResult class
                HttpURLConnection con = (HttpURLConnection) url.openConnection(); //open the connection with the url
                con.setRequestMethod("GET"); // use the GET method
                con.connect(); //start to connect

                inputStream = con.getInputStream(); // get the InputStream
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream)); //set the bufferedReader with the bytes
                String line = ""; // instance the get value

                while ((line = bufferedReader.readLine()) != null)
                    result += line; // get the value in the API and store the string to variable result

                inputStream.close(); // close the input Stream

            } catch (Exception e) {
                result = e.getMessage(); // show the Error message
            }
            return result;
        }


        protected void onPostExecute(String result) { //after the execute DoInBackground get the result variable
            try {

                JSONObject jObj = new JSONObject(result); // get the JSON
                opLeft = jObj.getInt("left"); // get value in JSON with the left key
                opRight = jObj.getInt("right"); // get value in JSON with the right key
                opGuess = jObj.getInt("guess"); // get value in JSON with the guess key

                i.putExtra("id", getIntent().getExtras().getString("id")); //put the Extra value to intent with the id key
                i.putExtra("Left", getIntent().getExtras().getInt("Left"));//put the Extra value to intent with the Left key
                i.putExtra("Right", getIntent().getExtras().getInt("Right"));//put the Extra value to intent with the Right key
                i.putExtra("startTime",getIntent().getExtras().getLong("startTime"));//put the Extra value to intent with the startTime key
                i.putExtra("Round",getIntent().getExtras().getInt("Round"));//put the Extra value to intent with the Round key
                i.putExtra("name",name);//put the Extra value to intent with the name key

                if(getIntent().hasExtra("guess")) // get the guess
                    i.putExtra("guess",getIntent().getExtras().getInt("guess"));//put the Extra value to intent with the guess key
                i.putExtra("opLeft",opLeft);//put the Extra value to intent with the opLeft key
                i.putExtra("opRight",opRight);//put the Extra value to intent with the opRight key
                i.putExtra("opGuess",opGuess);//put the Extra value to intent with the opGuess key
                startActivity(i); // start int intent
                finish(); // quit the finish

            } catch (JSONException e) {
                result = e.getMessage(); //show the error message
            }
        }
}
}
