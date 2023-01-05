package com.game.tictacteo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.game.tictacteo.localDB.GameLogDB;
import com.game.tictacteo.model.GameLog;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity {


    private Button btnRecordBack;
    private SurfaceView winStatePieChartView;
    private SurfaceHolder holder;
    private RecyclerView rvGameLogList;
    private ArrayList<GameLog> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        logs = GameLogDB.getInstance(this).readAllData();
        winStatePieChartView = (SurfaceView) findViewById(R.id.sfwinStatePieChartView);

        // For get Canvas in SurfaceView
        holder = winStatePieChartView.getHolder();

        if(logs.size() > 0){

            // If not callback will throw error due to SurfaceView.canvas is not created in onCreate()
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

                    float[] perc = calPercentage(logs);
                    Paint paint = new Paint();
                    paint.setAntiAlias(true);
                    paint.setStyle(Paint.Style.FILL_AND_STROKE);
                    paint.setStrokeWidth(4);

                    Canvas canvas = holder.lockCanvas();

                    RectF rectF = new RectF(225,50,winStatePieChartView.getWidth()-225,winStatePieChartView.getHeight()-50);
                    // draw the lose pie
                    paint.setColor(Color.RED);
                    canvas.drawArc(rectF, 0, 360*perc[0], true, paint);

                    // draw the win pie
                    paint.setColor(Color.GREEN);
                    canvas.drawArc(rectF, 360*perc[0], 360*perc[1], true, paint);

                    // draw the draw pie
                    paint.setColor(Color.YELLOW);
                    canvas.drawArc(rectF, 360*perc[0]+360*perc[1], 360*perc[2], true, paint);

                    // setup text paint
                    paint.setStrokeWidth(0);
                    paint.setTextSize(30);

                    // write Draw Text
                    canvas.drawRect(winStatePieChartView.getWidth()-160, winStatePieChartView.getHeight()-200, winStatePieChartView.getWidth()-135, winStatePieChartView.getHeight()-175, paint);
                    canvas.drawText("DRAW", winStatePieChartView.getWidth()-125, winStatePieChartView.getHeight()-177, paint);

                    // write Lose Text
                    paint.setColor(Color.RED);
                    canvas.drawRect(winStatePieChartView.getWidth()-160, winStatePieChartView.getHeight()-150, winStatePieChartView.getWidth()-135, winStatePieChartView.getHeight()-125, paint);
                    canvas.drawText("LOSE", winStatePieChartView.getWidth()-125, winStatePieChartView.getHeight()-127, paint);

                    // write Win Text
                    paint.setColor(Color.GREEN);
                    canvas.drawRect(winStatePieChartView.getWidth()-160, winStatePieChartView.getHeight()-100, winStatePieChartView.getWidth()-135, winStatePieChartView.getHeight()-75, paint);
                    canvas.drawText("WIN", winStatePieChartView.getWidth()-125, winStatePieChartView.getHeight()-77, paint);

                    holder.unlockCanvasAndPost(canvas);

                    //holder.lockCanvas(new Rect(0, 0, 0, 0));
                    //holder.unlockCanvasAndPost(canvas);
                }

                private float[] calPercentage(ArrayList<GameLog> data){
                    int[] count = new int[3]; // 0 -> lose, 1 -> win, 2 -> draw
                    int total = 0;
                    for(GameLog gl: data ) {
                        count[gl.getWinningStatus()]++;
                        total++;
                    }
                    Log.i("prc1", count[0]*1.0f/total+"");
                    Log.i("prc2", count[1]*1.0f/total+"");
                    Log.i("prc3", count[2]*1.0f/total+"");
                    return new float[]{count[0]*1.0f/total, count[1]*1.0f/total, count[2]*1.0f/total};

                }

                @Override
                public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

                }
            });
        }


        rvGameLogList = (RecyclerView) findViewById(R.id.rvGameLogList);

        btnRecordBack = (Button) findViewById(R.id.btnRecordBack);
        btnRecordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // create adapter for attached the log list
        UserRecordAdapter rankingAdapter = new UserRecordAdapter(this, logs);
        rvGameLogList.setAdapter(rankingAdapter);
        rvGameLogList.setLayoutManager(new LinearLayoutManager(this));

    }






}