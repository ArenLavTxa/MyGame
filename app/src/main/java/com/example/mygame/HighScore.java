package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HighScore extends AppCompatActivity {
    private int highScore;
    private int score;
    public int easyscore;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        Button back = findViewById(R.id.back);
        TextView high = findViewById(R.id.easyScore);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        score = intent.getIntExtra("HIGHSCORE", easyscore);
        mDatabase = FirebaseDatabase.getInstance().getReference("highscore");

        mDatabase.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Integer firebaseHighScore = task.getResult().getValue(Integer.class);
                if (firebaseHighScore != null && score > firebaseHighScore) {
                    highScore = score;
                    mDatabase.setValue(highScore);
                } else {
                    highScore = firebaseHighScore != null ? firebaseHighScore : 0;
                }
                high.setText("Your highest score is: " + highScore);
            } else {
                highScore = score;
                high.setText("Your highest score is: " + highScore);
            }
        });
        high.setText("Your highest score is: " + highScore);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HighScore.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


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

//package com.example.mygame;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//public class HighScore extends AppCompatActivity {
//    public int highscore;
//    private int score;
//    private int easyscore;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_high_score);
//
//        Button back = findViewById(R.id.back);
//        TextView high = findViewById(R.id.easyScore);
//
//        Intent intent = getIntent();
//        score = intent.getIntExtra("HIGHSCORE",easyscore);
//        if(score>highscore)
//        high.setText("Your highest score is: " + score);
//        else {
//            high.setText("Your highest score is: " + highscore);
//        }
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(HighScore.this, MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//    }
//}
