package com.game.tictacteo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.game.tictacteo.model.UserRanking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    private Button btnBack;
    private final static String API_ENDPOINT = "http://192.168.76.3/ranking_api.php";
    private LoadingDialog ld;
    private ArrayList<UserRanking> users;
    private RecyclerView rvRankingListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        // Load Dailog
        ld = new LoadingDialog(this);
        users = new ArrayList<UserRanking>();

        btnBack = (Button) findViewById(R.id.btnBack);
        rvRankingListView = (RecyclerView) findViewById(R.id.rvRankingList);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // show loading dialog
        ld.show("fetching...");
        new Thread(() -> {
            try{
                // fetch API
                URL url = new URL(API_ENDPOINT);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
                String data = buffer.readLine();
                StringBuffer json = new StringBuffer();
                while(data != null){
                    json.append(data);
                    data = buffer.readLine();
                }
                // JSON List to ArrayList
                JSONArray jsonArray = new JSONArray(String.valueOf(json));
                for(int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    String name = jsonObj.getString("Name");
                    int duration = jsonObj.getInt("Duration");
                    users.add(new UserRanking(name, duration));
                    Log.i("ranking", "add" + name);
                }

                runOnUiThread(() -> {
                    ld.hide();
                    UserRankingAdapter rankingAdapter = new UserRankingAdapter(this, users);
                    rvRankingListView.setAdapter(rankingAdapter);
                    rvRankingListView.setLayoutManager(new LinearLayoutManager(RankingActivity.this));
                });


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ).start();

    }
}