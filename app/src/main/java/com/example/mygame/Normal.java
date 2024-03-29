package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Normal extends AppCompatActivity {
    private String[] easy = {"a_ack", "c_rt", "scar_", "li_ht", "_night", "bro_oli", "ball_n", "sheri_", "su_e_", "ze_yr"};
    private String[] easyans = {"attack", "court", "scarce", "light", "knight", "broccoli", "balloon", "sheriff", "success", "zephyr"};
    private Map<String, Integer> answers = new HashMap<>();
    private int currentIndex = 0;
    private TextView word;
    private EditText userInput;
    private ImageView pictureOfWord;
    public int score = 0;
    public int mul = 1;
    private int i = 0;
    private int mistake = 0;
    private TextView timerTextView;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 92000;
    private boolean timerRunning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        word = findViewById(R.id.word);
        userInput = findViewById(R.id.userInput);
        timerTextView = findViewById(R.id.timerTextView);
        Button checkButton = findViewById(R.id.accept);
        shuffleArrays();
        startTimer();
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTypedWord = userInput.getText().toString().toLowerCase();
                if (currentIndex >= easy.length - 1 || mistake == 2) {
                    if (userTypedWord.equals(easyans[currentIndex])) {
                        score = score + 100 * mul;
                    }
                    Intent intent = new Intent(Normal.this, Score.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);
                    finish();
                } else if (userTypedWord.equals(easyans[currentIndex])) {
                    currentIndex = (currentIndex + 1) % easy.length;
                    word.setText(easy[currentIndex]);
                    score = score + 100 * mul;
                    mul++;
                    Toast.makeText(Normal.this, "Correct! Score: " + score, Toast.LENGTH_SHORT).show();
                    userInput.setText("");
                } else {
                    currentIndex = (currentIndex + 1) % easy.length;
                    word.setText(easy[currentIndex]);
                    mul = 1;
                    mistake++;
                    Toast.makeText(Normal.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    userInput.setText("");
                }
                i++;
            }
        });
    }

    private void shuffleArrays() {
        Random random = new Random();
        for (int i = easy.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            String tempEasy = easy[index];
            easy[index] = easy[i];
            easy[i] = tempEasy;
            String tempEasyans = easyans[index];
            easyans[index] = easyans[i];
            easyans[i] = tempEasyans;
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                Intent intent = new Intent(Normal.this, Score.class);
                intent.putExtra("SCORE", score);
                startActivity(intent);
                finish();
            }
        }.start();
        timerRunning = true;
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);

        if (timeLeftInMillis < 1000) {
            timerRunning = false;
            Intent intent = new Intent(this, Score.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}