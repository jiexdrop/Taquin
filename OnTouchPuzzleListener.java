package com.jnvarzea.taquin;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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

    /**
     * Find the coordinates of the blank piece that is next to the passed coordinates
     *
     * @param x coordinates of the touched view in x
     * @param y coordinates of the touched view in x
     * @return the piece coordinates to exchange
     */
    private Coordinate FindBlankPieceCoordinates(float x, float y) {
        Coordinate coordinate = new Coordinate(x, y, puzzlePieces.getBlankView().getHeight());
        for (int i = 0; i < puzzlePieces.getCount(); i++) {
            if (puzzlePieces.getItem(i).getAlpha() == 0f) {
                Coordinate emptyImageView = new Coordinate(puzzlePieces.getItem(i).getX(),
                        puzzlePieces.getItem(i).getY(), puzzlePieces.getItem(i).getWidth());
                if (puzzlePieces.NearMe(coordinate, emptyImageView)) {
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
                System.out.println(v.getX() + "/" + v.getY() + "-" + coordinate.realY + "/" + coordinate.realX);

                TranslateAnimation animation = new TranslateAnimation(0,0,0,0);
                if (coordinate.realX > v.getX()) {
                    animation = new TranslateAnimation(0, v.getWidth(), 0, 0);
                }
                else if (coordinate.realX < v.getX()) {
                    animation = new TranslateAnimation(0, -v.getWidth(), 0, 0);
                }
                else if (coordinate.realY > v.getY()) {
                    animation = new TranslateAnimation(0, 0, 0, v.getHeight());
                }
                else if (coordinate.realY < v.getY()) {
                    animation = new TranslateAnimation(0, 0, 0, -v.getHeight());
                }

                animation.setDuration(200);

                v.startAnimation(animation);


                animation.setAnimationListener(new SlideListner(v, coordinate));

                puzzlePieces.Win();
                return true;
            case MotionEvent.ACTION_MOVE:

                return true;
            case MotionEvent.ACTION_UP:
                return true;
        }
        return false;
    }

    public class SlideListner implements Animation.AnimationListener {
        View v;
        Coordinate c;

        public SlideListner(View v, Coordinate c) {
            this.v = v;
            this.c = c;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            v.setX(c.realX);
            v.setY(c.realY);
            v.clearAnimation();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
