package com.example.a15puzzleapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EndOfGame extends AppCompatActivity {
    private TextView score;
    private Button startNewGame;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_of_game);
        score=(TextView)findViewById(R.id.Score);
        Intent intent = getIntent();
        int points=intent.getIntExtra(MainActivity.Score_Holder,0);

        score.setText("You earned" + points + "points!");
        startNewGame();


    }

    public void startNewGame(){
       startNewGame=(Button)findViewById(R.id.StartNewGameButton);
        startNewGame.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(EndOfGame.this,
                                MainActivity.class);
                        startActivity(myIntent);

                    }
                }

        );

    }
}
