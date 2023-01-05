package com.game.tictacteo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class InitActivity extends AppCompatActivity {

    private TextView tvLoadingMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
        // hidden Action bar
//        getSupportActionBar().hide();

        tvLoadingMes = (TextView) findViewById(R.id.tvLoadingMes);
        // For Some setup things
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}