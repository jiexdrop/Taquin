package com.jnvarzea.taquin;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jorge Nogueira on 04/03/17.
 */

public class OnTouchPuzzleListener implements OnTouchListener {
    PuzzlePieces puzzlePieces;

    public OnTouchPuzzleListener(PuzzlePieces puzzlePieces) {
        this.puzzlePieces = puzzlePieces;

    }

    private Coordinate FindBlankPieceCoordinates(float x, float y) {
        Coordinate coordinate = new Coordinate(x,y);
        for (int i = 0; i<puzzlePieces.getCount(); i++) {
            if (puzzlePieces.getItem(i).getAlpha() == 0f) {
                Coordinate emptyImageView = new Coordinate(puzzlePieces.getItem(i).getX(),
                        puzzlePieces.getItem(i).getY()).ToRound(puzzlePieces.getItem(i).getWidth()+1f);
                if(NearMe(coordinate.ToRound(puzzlePieces.getItem(i).getWidth()+1f), emptyImageView)) {
                    coordinate = new Coordinate(puzzlePieces.getItem(i).getX(), puzzlePieces.getItem(i).getY());
                    puzzlePieces.getItem(i).setX(x);
                    puzzlePieces.getItem(i).setY(y);
                }
                return coordinate.ToBackUp();
            }
        }
        return coordinate.ToBackUp();
    }


    private boolean NearMe(Coordinate a, Coordinate b){
        //System.out.println(a.x + " " + a.y + " - " + b.x + " " + b.y );
        if(a.x+1 == b.x && a.y == b.y){
            return true;
        }
        if(a.x-1 == b.x && a.y == b.y){
            return true;
        }
        if(a.y+1 == b.y && a.x == b.x){
            return true;
        }
        if(a.y-1 == b.y && a.x == b.x){
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Coordinate coordinate = FindBlankPieceCoordinates(v.getX(), v.getY());
                v.setX(coordinate.x);
                v.setY(coordinate.y);
                return true;
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }
}
