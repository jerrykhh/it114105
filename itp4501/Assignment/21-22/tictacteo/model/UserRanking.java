package com.game.tictacteo.model;

public class UserRanking {
    private String name;
    private int duration;

    public UserRanking(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }
}
