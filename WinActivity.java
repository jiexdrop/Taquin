package com.jnvarzea.taquin;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Jorge Nogueira on 09/03/17.
 */


public class WinActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        DisplayMetrics displayMetrics= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);


        getWindow().setLayout(((Double)(displayMetrics.widthPixels*0.8)).intValue(),
                ((Double)(displayMetrics.heightPixels*0.2)).intValue());

    }

    public void okButton(View view){
        startActivity(new Intent(this,ChooseImageActivity.class));
        finish();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                GameActivity.appCompatActivity.finish();
            }

        }, 5000);
    }
}