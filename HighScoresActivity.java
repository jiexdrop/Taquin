package com.jnvarzea.taquin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HighScoresActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    TextView lastScore;
    Set<String> scores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        linearLayout = (LinearLayout) findViewById(R.id.activity_high_scores);
        lastScore = (TextView) findViewById(R.id.lastScore);

        SharedPreferences sharedPref = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        lastScore.setText(sharedPref.getString("LAST_SCORE",getString(R.string.not_yet_played)));

        LoadScores();
    }

    private void LoadScores(){
        SharedPreferences sharedPref = getSharedPreferences(getPackageName(),Context.MODE_PRIVATE);
        Set<String> copy = sharedPref.getStringSet("SCORES", new HashSet<String>() );
        scores = new HashSet<>(copy);
        List<String> toSort = new ArrayList<>(scores);
        Collections.sort(toSort);

        for(String s:toSort) {
            TextView toAdd = new TextView(getApplicationContext());
            toAdd.setText(s);
            toAdd.setGravity(Gravity.CENTER_HORIZONTAL);
            toAdd.setTextSize(22);
            linearLayout.addView(toAdd);
        }
    }
}
