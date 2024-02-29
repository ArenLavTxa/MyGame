package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class ChooseTheStage extends AppCompatActivity {
    public int stage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_the_stage);

        Button easy = findViewById(R.id.easy);
        Button normal = findViewById(R.id.normal);
        Button hard = findViewById(R.id.hard);
        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ChooseTheStage.this, Easy.class);
                startActivity(intent);
                stage=1;
            }
        });
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the second activity
                Intent intent = new Intent(ChooseTheStage.this, Easy.class );
                startActivity(intent);
                stage=2;
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the second activity
                Intent intent = new Intent(ChooseTheStage.this, Easy.class );
                startActivity(intent);
                stage=3;
            }
        });
    }
}