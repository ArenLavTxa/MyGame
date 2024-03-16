package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HighScore extends Score {
public int easyHighScore;
public  int normalHighScore;
public  int hardHighScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        Button back = findViewById(R.id.back);
        TextView textView=findViewById(R.id.easyScore);
        if(easyscore>easyHighScore){
            easyHighScore=easyscore;
            textView.setText(String.valueOf(easyHighScore));
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScore.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}