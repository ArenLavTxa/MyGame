package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

public class EasyScore extends AppCompatActivity {
    public int score;
    private int highestScore;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_score);

        // Load the highest score from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyGamePreferences", Context.MODE_PRIVATE);
        highestScore = sharedPreferences.getInt("highestScore", 0);

        TextView over = findViewById(R.id.score);
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", 0);
        over.setText("You got " + score);

        // Display the highest score
        TextView easyScoreTextView = findViewById(R.id.easyScore);
        easyScoreTextView.setText(String.valueOf(highestScore));

        // Check if the current score is higher than the highest score
        if (score > highestScore) {
            highestScore = score;
            // Save the new highest score to SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("highestScore", highestScore);
            editor.apply();
        }

        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EasyScore.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
//public class EasyScore extends AppCompatActivity {
//   public int score;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_easy_score);
//
//        TextView over = findViewById(R.id.score);
//        Intent intent = getIntent();
//        score = intent.getIntExtra("SCORE", 0);
//        over.setText("You got " + score);
//
//
//        Button exit = findViewById(R.id.exit);
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(EasyScore.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}