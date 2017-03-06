package com.jnvarzea.taquin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
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
    ImageAdapter adapterView;
    int size = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        levelBar = (SeekBar) findViewById(R.id.levelBar);
        puzzlePager = (PuzzlePager) findViewById(R.id.puzzleImages);

        adapterView = new ImageAdapter(this);
        puzzlePager.setAdapter(adapterView);

    }

    public void toGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        //System.out.println(((PuzzlePager)view).getCurrentItem());

        intent.putExtra("IMAGE", "m" + (puzzlePager.getCurrentItem()));

        size = levelBar.getProgress() + 3;
        intent.putExtra("SIZE", size);
        startActivity(intent);
    }

    public void toGameActivityFromImage(){
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("IMAGE_BITMAP", adapterView.getBitmapAt(puzzlePager.getCurrentItem()));
        size = levelBar.getProgress() + 3;
        intent.putExtra("SIZE", size);
        startActivity(intent);
    }

    public void toHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void toCameraIntent(View view) {
        capturePhoto();
    }


    public void capturePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ImageView imageViewToAdd = new ImageView(this);
            imageViewToAdd.setImageBitmap(imageBitmap);

            imageViewToAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toGameActivityFromImage();
                }
            });

            adapterView.AddImage(imageViewToAdd);
            puzzlePager.setAdapter(adapterView);
        }
    }
}
