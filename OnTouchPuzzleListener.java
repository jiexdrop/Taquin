package com.jnvarzea.taquin;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Jorge Nogueira on 04/03/17.
 */

public class OnTouchPuzzleListener implements OnTouchListener {
    ArrayList<ImageView> puzzlePiecesViews;
    public OnTouchPuzzleListener(ArrayList<ImageView> puzzlePiecesViews) {
        this.puzzlePiecesViews = puzzlePiecesViews;
    }

    private Coordinate FindBlankPieceCoordinates(float x, float y){
        for(ImageView imageView:puzzlePiecesViews){
            if(imageView.getAlpha()==0f){
                imageView.setX(x);
                imageView.setY(y);
                return new Coordinate(imageView.getX(), imageView.getY());
            }
        }
        return null;
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
