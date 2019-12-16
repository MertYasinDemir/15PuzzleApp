package com.example.a15puzzleapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Timer;

import static android.content.Context.MODE_PRIVATE;


public class ScoreHandler {

    public static final String Shared_Pref = "HighestScore";
    public static final String Score="Score";


    public static Integer getHighestScore(Context applicationContext) {
        SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(Shared_Pref, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Integer highScore=sharedPreferences.getInt(Score, 0);
        editor.apply();
        return highScore;
    }

}
