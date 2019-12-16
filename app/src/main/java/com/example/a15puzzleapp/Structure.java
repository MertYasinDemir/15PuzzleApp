package com.example.a15puzzleapp;

import android.widget.TableLayout;

import java.util.Random;

public class Structure {

    int[] tile;
    boolean boardCreated;
    private int[] numbers={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};


    public Structure(){
        tile=numbers;
        boardCreated=false;
    }

    public void startGame(){
        if(!boardCreated){
            tile=numbers;
            boardCreated=true;
        }

        shuffle();
    }

    public void swap(int i, int j){
        int temp = tile[i];
        tile[i] = tile[j];
        tile[j] = temp;
    }

    public void shuffle(){
        Random random = new Random();

        for (int i=16; i>1; i--) {
            int j = random.nextInt(i);
            swap(i-1, j);
        }
    }

    public void move(int position){

        boolean leftPosition=(position%4==0);
        boolean rightPosition=(position%4==3);
        boolean topPosition=(position<4);
        boolean bottomPosition=(position>11);

        if (!leftPosition && tile[position-1] == 0)
            swap(position, position -1);

        else if (!rightPosition && tile[position +1] ==0)
            swap(position, position+1);

        else if (!topPosition && tile[position-4] == 0)
            swap(position,position-4);

        else if(!bottomPosition && tile[position+4]==0)
            swap(position, position+4);

        else return;
    }

    public boolean checkIfSolved() {
        for (int i=0; i<15; i++){
            if(tile[i]!=(i+1))
                return false;
        }
        return true;
    }


}
