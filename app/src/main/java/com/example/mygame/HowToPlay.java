package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HowToPlay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);
        TextView info=findViewById(R.id.info);
        Button back=findViewById(R.id.backinfo);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HowToPlay.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        info.setText("In this game there are words with missing letters and you need write this all word correct." +
                "In easy mode you get hint by picture of this word. In normal you need to understand word by sentences." +
                "In hard it the same , but with short story." +"You get points for correct answer."+
                "If you answer correct 2 or more times you will get extra points,but if you answer incorrect you will lose your seria.Youlose if you answer 3 times incorrect or if your timmer over.You get your point in the end of the game." );
    }
}