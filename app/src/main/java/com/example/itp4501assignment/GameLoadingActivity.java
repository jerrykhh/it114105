package com.example.itp4501assignment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GameLoadingActivity extends AppCompatActivity {
    Intent intent;
    RelativeLayout relLayoutTop, relLayoutBottom;
    String name, id, country;
    FetchPagetask task=null;
    TextView tvLoading, tvName, tvCountry;
    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() { // set the runnable (thread)

            relLayoutTop.setVisibility(View.VISIBLE); // display top relative Layout
            relLayoutBottom.setVisibility(View.VISIBLE); // display bottom relative Layout
        }
    };
    Runnable start = new Runnable() {
        @Override
        public void run() {// set the runnable (thread)
            intent.putExtra("id",id); // put the id to intent with id key
            intent.putExtra("name",name); // put the name to intent with name key
            startActivity(intent); // start activity

            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            //display with the animation
            finish();
            //quit the activity
        }
    };
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameload_main);// get Design layout in layout/gameload_main
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        tvLoading = findViewById(R.id.tvMatching);// set variable to adapter tvMatching
        tvName = findViewById(R.id.tvName);// set variable to adapter tvName
        tvCountry = findViewById(R.id.tvCountry);// set variable to adapter tvCountry
        relLayoutTop = findViewById(R.id.relLayoutTop);// set variable to adapter relLayoutTop
        relLayoutBottom = findViewById(R.id.relLayoutBottom);// set variable to adapter relLayoutBottom
        intent= new Intent(this, GameStartAcivity.class);//instance the intent object

        if(task ==null ||
                task.getStatus().equals(AsyncTask.Status.FINISHED)){ // check the task is nul
            task = new FetchPagetask();
            task.execute("https://4qm49vppc3.execute-api.us-east-1.amazonaws.com/Prod/itp4501_api/opponent/0");
            //send the api url to FetchPagetask class
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
                id = jObj.getString("id");// get value in JSON with the id key
                name = jObj.getString("name");// get value in JSON with the name key
                country = jObj.getString("country");// get value in JSON with the country key

                tvName.setText("Name: "+ name);  //set text of the Name
                tvCountry.setText("Country: "+ country);//set text of the Country
                tvLoading.setText("Matched");//set text of the Loading
                handler.postDelayed(runnable, 1000); //1000 is the timeout for the animation
                handler.postDelayed(start,3000);//3000 is the timeout for the start the activity
            } catch (Exception e) {
                result = e.getMessage(); // show the error message
            }
        }


    }

}