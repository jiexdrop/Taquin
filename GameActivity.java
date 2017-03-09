package com.jnvarzea.taquin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
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
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameActivity extends AppCompatActivity {
    public static AppCompatActivity appCompatActivity;
    private PuzzlePieces puzzlePieces;
    private GridView puzzleGridView;
    private Chronometer chronometer;
    private int size;
    private String levelName;
    private Set<String> scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        appCompatActivity = this;

        puzzleGridView = (GridView) findViewById(R.id.puzzleGridView);
        chronometer = (Chronometer) findViewById(R.id.chrono);

        String uriImage = getIntent().getStringExtra("IMAGE");
        size = getIntent().getIntExtra("SIZE", 3);

        if (uriImage != "" && uriImage != null) {
            puzzlePieces = new PuzzlePieces(this, uriImage, size);
            levelName = getResources().getString(R.string.image) + " - " + uriImage;
        } else {
            Bitmap bitmap = getIntent().getParcelableExtra("IMAGE_BITMAP");
            levelName = getResources().getString(R.string.personal_image);
            puzzlePieces = new PuzzlePieces(this, bitmap, size);
        }

        LoadScores();

        puzzleGridView.setNumColumns(size);
        puzzleGridView.setAdapter(puzzlePieces);
    }

    public void startGame(View view) {
        view.setVisibility(View.INVISIBLE);
        chronometer.start();
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if (puzzlePieces.isSolved()) {
                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.win), Toast.LENGTH_LONG).show();
                    SaveScores();
                    chronometer.stop();
                    puzzlePieces.getBlankView().setAlpha(1f);

                    GameActivity.this.startActivity(new Intent(GameActivity.this,WinActivity.class));
                }
            }
        });
        puzzlePieces.Shuffle();
    }

    private void SaveScores() {
        SharedPreferences sharedPref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("LAST_SCORE", levelName + " : " + chronometer.getText() + " / "
                + getResources().getString(R.string.difficulty) + " = " + size);
        scores.add(levelName + " : " + chronometer.getText() + " / "
                + getResources().getString(R.string.difficulty) + " = " + size);
        editor.putStringSet("SCORES", scores);

        editor.commit();
    }

    private void LoadScores() {
        SharedPreferences sharedPref = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        Set<String> copy = sharedPref.getStringSet("SCORES", new HashSet<String>());
        scores = new HashSet<>(copy);
    }

}
