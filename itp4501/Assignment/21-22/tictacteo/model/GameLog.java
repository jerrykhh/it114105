package com.game.tictacteo.model;

import java.util.Date;
import java.sql.Time;

public class GameLog {
    private Date playDate;
    private Time playTime;
    private int duration;
    private int winningStatus;

    public GameLog(Date playDate, Time playTime, int duration, int winningStatus) {
        this.playDate = playDate;
        this.playTime = playTime;
        this.duration = duration;
        this.winningStatus = winningStatus;
    }

    public Date getPlayDate(){
        return playDate;
    }

    public Time getPlayTime(){
        return playTime;
    }

    public int getDuration(){
        return duration;
    }

    public int getWinningStatus(){
        return winningStatus;
    }

    public String winStateToString() { return winStateToString(this.winningStatus);}

    public String winStateToString(int i) {
        if( i == 0)
            return "Lose";
        else if ( i == 1)
            return "Win";
        else if ( i == 2)
            return "Draw";
        return "";
    }
}
