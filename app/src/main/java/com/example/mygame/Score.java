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
    public int normalscore;
    public int hardscore;
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
                    Intent intent = new Intent(Score.this, HighScore.class);
                    if(stage==1) {
                        easyscore=score;
                        intent.putExtra("SCORE", easyscore);
                    }
                if(stage==2) {
                    normalscore=score;
                    intent.putExtra("SCORE", normalscore);
                }
                if(stage==3) {
                    hardscore=score;
                    intent.putExtra("SCORE", hardscore);
                }
                        startActivity(intent);
                        finish();
            }
        });
    }
}