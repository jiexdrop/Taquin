package com.jnvarzea.taquin;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jorge Nogueira on 04/03/17.
 */

public class OnTouchPuzzleListener implements OnTouchListener {
    private PuzzlePieces puzzlePieces;

    public OnTouchPuzzleListener(PuzzlePieces puzzlePieces) {
        this.puzzlePieces = puzzlePieces;
    }

    private Coordinate FindBlankPieceCoordinates(float x, float y) {
        Coordinate coordinate = new Coordinate(x,y,puzzlePieces.getItem(0).getWidth()+1f);
        for (int i = 0; i<puzzlePieces.getCount(); i++) {
            if (puzzlePieces.getItem(i).getAlpha() == 0f) {
                Coordinate emptyImageView = new Coordinate(puzzlePieces.getItem(i).getX(),
                        puzzlePieces.getItem(i).getY(), puzzlePieces.getItem(i).getWidth());
                if(puzzlePieces.NearMe(coordinate, emptyImageView)) {
                    coordinate = new Coordinate(puzzlePieces.getItem(i).getX(),
                            puzzlePieces.getItem(i).getY(),
                            puzzlePieces.getItem(i).getWidth());
                    puzzlePieces.getItem(i).setX(x);
                    puzzlePieces.getItem(i).setY(y);
                }
                return coordinate;
            }
        }
        return coordinate;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Coordinate coordinate = FindBlankPieceCoordinates(v.getX(), v.getY());
                v.setX(coordinate.realX);
                v.setY(coordinate.realY);
                puzzlePieces.Win();
                return true;
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }
}
