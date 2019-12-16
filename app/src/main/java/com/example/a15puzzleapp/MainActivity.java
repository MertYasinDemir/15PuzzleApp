package com.example.a15puzzleapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends Activity {

    private Structure structure;
    private CreateBoard createBoard;
    private TextView elapsedTime;
    private TextView bestScore;
    private TableLayout board;
    private int minute=0;
    private int second=0;
    public static String Score_Holder;
    int score=1000;
    int highestScore=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        elapsedTime=(TextView)findViewById(R.id.ElapsedTime);
        bestScore=(TextView)findViewById(R.id.HighestScore);
        board=(TableLayout)findViewById(R.id.Board);
        Button startNewGame=(Button)findViewById(R.id.StartButton);
        startNewGame.setOnClickListener(clickStartNewGame);
        createBoard=new CreateBoard(getApplicationContext(), structure, board, this);

        createBoard.createGameBoard();
        Integer hScore = ScoreHandler.getHighestScore(getApplicationContext());
        bestScore.setText(hScore);

    }

    private boolean elapsedTime(){
        new Thread(new Runnable() {
            public void run() {
                while(true) {
                    if(structure.checkIfSolved())
                        return;
                    runOnUiThread(new Runnable() {
                        public void run() {
                            final String showTime = String.format("Time: %02d:%02d", minute, second);
                            elapsedTime.setText(showTime);
                            second++;
                            if(second == 60) {
                                minute++;
                                second = 0;
                            }
                        }
                    });

                    SystemClock.sleep(1000);
                }
            }
        }).start();
        return false;
    }

    public void score(){
        while(elapsedTime()){
            score -= 10;
        }
        if(score>highestScore){
            highestScore=score;
        }
        if(structure.checkIfSolved()){
            Intent intent=new Intent(this,EndOfGame.class);
            intent.putExtra(Score_Holder, score);
            startActivity(intent);
            finish();
        }

    }

    public OnClickListener clickStartNewGame=new OnClickListener() {
        @Override
        public void onClick(View v) {
            structure.startGame();
        }
    };
}
