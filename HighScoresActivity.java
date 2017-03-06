package com.jnvarzea.taquin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoresActivity extends AppCompatActivity {

    TextView lastScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        lastScore = (TextView) findViewById(R.id.lastScore);

        SharedPreferences sharedPref = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        lastScore.setText(sharedPref.getString("LAST_SCORE",getString(R.string.not_yet_played)));
    }
}
