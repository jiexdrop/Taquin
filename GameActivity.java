package com.jnvarzea.taquin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameActivity extends AppCompatActivity {
    private PuzzlePieces puzzlePieces;
    private GridView puzzleGridView;
    private Chronometer chronometer;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        puzzleGridView = (GridView) findViewById(R.id.puzzleGridView);
        chronometer = (Chronometer) findViewById(R.id.chrono);

        String uriImage = getIntent().getStringExtra("IMAGE");
        size = getIntent().getIntExtra("SIZE", 3);

        puzzlePieces = new PuzzlePieces(this, uriImage, size);

        puzzleGridView.setNumColumns(size);
        puzzleGridView.setAdapter(puzzlePieces);
    }

    public void startGame(View view) {
        view.setVisibility(View.INVISIBLE);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(puzzlePieces.isSolved()) {
                    Toast.makeText(getApplicationContext(), "WIN", Toast.LENGTH_LONG).show();
                    chronometer.stop();
                    puzzlePieces.getBlankView().setAlpha(1f);
                }
            }
        });
        puzzlePieces.Shuffle();
    }


}
