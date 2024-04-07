package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Score extends ChooseTheStage {
    public int score;
    public int easyscore;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TextView over = findViewById(R.id.score);
        Intent intent = getIntent();
        score = intent.getIntExtra("SCORE", easyscore);
        over.setText("You got " + score);
        Button exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    easyscore = score;
                    Intent intent = new Intent(Score.this, HighScore.class);
                    intent.putExtra("HIGHSCORE", easyscore);
                    startActivity(intent);
                    finish();
            }
        });
    }
}
