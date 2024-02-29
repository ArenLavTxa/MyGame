package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EasyScore extends AppCompatActivity {
    private int score;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_score);

        TextView over = findViewById(R.id.score);
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", 0);
        over.setText("You got " + score);


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