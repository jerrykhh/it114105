package com.game.tictacteo.localDB;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.game.tictacteo.model.GameLog;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GameLogDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "GameLog.db";
    private static final String GAMESLOG_TABLE_NAME = "GamesLog";
    private static final int DATABASE_VERSION = 1;
    // Singleton pattern
    private static GameLogDB instance = null;

    public static GameLogDB getInstance(Context ctx) {
        if (instance == null)
            instance = new GameLogDB(ctx.getApplicationContext());
        return instance;
    }

    public GameLogDB(Context context) {
        // applcation_context, db_name, factory, db_version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Database creating");
        String sql = "CREATE TABLE " + GAMESLOG_TABLE_NAME + " ( " +
                "gameID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "playDate TEXT NOT NULL, " +
                "playTime TEXT NOT NULL, " +
                "duration INTEGER NOT NULL, " +
                "winningStatus INTEGER NOT NULL);";

        db.execSQL(sql);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GAMESLOG_TABLE_NAME + ";");
        onCreate(db);
    }

    // Add log
    public void addLog(GameLog log) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");

        values.put("playDate", dateFormat.format(log.getPlayDate()));
        values.put("playTime", timeFormat.format(log.getPlayTime()));
        values.put("duration", log.getDuration());
        values.put("winningStatus", log.getWinningStatus());

        db.insert(GAMESLOG_TABLE_NAME, null, values);
        db.close();
    }

    // select all data
    public ArrayList<GameLog> readAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(GAMESLOG_TABLE_NAME, new String[]{"playDate", "playTime", "duration", "winningStatus"}, null, null, null, null, "gameId");
        ArrayList<GameLog> data = new ArrayList<GameLog>();

        // Time formatter
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa");

        try {
            while (cursor.moveToNext()) {

                // yyyy-MM-dd striing to date object
                Date playDate = Date.valueOf(cursor.getString(0));
                Log.i("db", cursor.getString(1));
                Time playTime = new Time(timeFormat.parse(cursor.getString(1)).getTime());
                int duration = cursor.getInt(2);
                short winningStatus = cursor.getShort(3);

                data.add(new GameLog(playDate, playTime, duration, winningStatus));

            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        db.close();
        return data;

    }
}
