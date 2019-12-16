package com.example.a15puzzleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CreateBoard {
    private Structure structure;
    private Context context;
    private TableLayout gameBoard;
    MainActivity mainActivity;

    public CreateBoard(Context context,Structure structure, TableLayout gameBoard, MainActivity mainActivity){
        this.structure=structure;
        this.context=context;
        this.gameBoard=gameBoard;
        this.mainActivity=mainActivity;
    }

    public void createGameBoard(){
        for(int i=0; i<4; i++){
            TableRow tableRow=new TableRow(context);

            for(int j=0; j<4; j++){
                final int position=i*4+j;
                int tileValue=structure.tile[i*4+j];
                TextView tile=new TextView(context);
                tile.setWidth(250);
                tile.setHeight(250);
                tile.setGravity(Gravity.CENTER);

                if(tileValue!=0){
                    tile.setText(String.valueOf(tileValue));
                    tile.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            touchTile(position);
                        }
                    });
                }
                tableRow.addView(tile);

            }
            gameBoard.addView(tableRow);
        }
    }


    public void touchTile(int position){
        structure.move(position);
        int count=gameBoard.getChildCount();
        for(int i=0; i<count; i++){
            View child=gameBoard.getChildAt(i);
            if(child instanceof TableRow)((ViewGroup) child).removeAllViews();
        }
        createGameBoard();
    }

}
