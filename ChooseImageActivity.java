package com.jnvarzea.taquin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChooseImageActivity extends AppCompatActivity {
    ImageView image;
    int size = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image);

        image = (ImageView) findViewById(R.id.imageView);
    }

    public void toGameActivity(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("IMAGE", "m1");
        intent.putExtra("SIZE", size);
        startActivity(intent);
    }
}
