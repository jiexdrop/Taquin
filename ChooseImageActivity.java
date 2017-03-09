package com.jnvarzea.taquin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ChooseImageActivity extends AppCompatActivity {
    SeekBar levelBar;
    PuzzlePager puzzlePager;
    ImageAdapter adapterView;
    Uri photoToAddPath;
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

        intent.putExtra("IMAGE", "m" + (puzzlePager.getCurrentItem()));

        size = levelBar.getProgress() + 3;
        intent.putExtra("SIZE", size);
        startActivity(intent);
        finish();
    }

    public void toGameActivityFromImage(Bitmap bitmap) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("IMAGE_BITMAP", bitmap);
        size = levelBar.getProgress() + 3;
        intent.putExtra("SIZE", size);
        startActivity(intent);
        finish();
    }

    public void toHighScoresActivity(View view) {
        Intent intent = new Intent(this, HighScoresActivity.class);
        startActivity(intent);
    }

    public void toCameraIntent(View view) {
        capturePhoto();
    }


    /**
     * Capture photo to 1
     */
    public void capturePhoto() {
        try {
            Intent captureIntent = new Intent(
                    MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(captureIntent, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                photoToAddPath = data.getData();
                doCrop(photoToAddPath);
            } else if (requestCode == 2) {
                Bundle extras = data.getExtras();
                final Bitmap bitmap = extras.getParcelable("data");
                ImageView imageViewToAdd = new ImageView(this);
                imageViewToAdd.setImageBitmap(bitmap);
                adapterView.AddImage(imageViewToAdd);
                puzzlePager.setAdapter(adapterView);
                imageViewToAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toGameActivityFromImage(bitmap);
                    }
                });
                puzzlePager.setCurrentItem(puzzlePager.getAdapter().getCount());
            }
        }
    }

    /**
     * Crop the image to 2
     * @param imageFile imagefile
     */
    private void doCrop(Uri imageFile) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(imageFile, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            cropIntent.putExtra("outputX", 512);
            cropIntent.putExtra("outputY", 512);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
