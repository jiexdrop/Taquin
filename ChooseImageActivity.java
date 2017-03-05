package com.jnvarzea.taquin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ViewSwitcher;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChooseImageActivity extends AppCompatActivity {
    SeekBar levelBar;
    PuzzlePager puzzlePager;
    int size = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        levelBar = (SeekBar) findViewById(R.id.levelBar);
        puzzlePager = (PuzzlePager) findViewById(R.id.puzzleImages);

        ImageAdapter adapterView = new ImageAdapter(this);
        puzzlePager.setAdapter(adapterView);

    }

    public void toGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        //System.out.println(((PuzzlePager)view).getCurrentItem());
        intent.putExtra("IMAGE", "m"+ (puzzlePager.getCurrentItem()));
        size = levelBar.getProgress() + 3;
        intent.putExtra("SIZE", size);
        startActivity(intent);
    }

    public void toHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }
}
