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
    private String[] easy = {"She received a bask_t full of fru_t for her birthday",
    "The w_ther was gloomy, with thick cl_ds covering the sky",
    "He stru_le to remember the correct spe_ings of difficult words",
    "The c_irping of the b_rds woke her up early in the morning",
            "She enjoyed the be_ty of the picturesq_ countryside during her road trip"
    };
    private String[] easyans = {"basket fruit", "weather clouds", "struggle spelling", "chirping bards", "beauty picturesque"};
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
    private long timeLeftInMillis = 60000;
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
        Random random = new Random();
        int randomIndex = random.nextInt(easy.length);
        String firstWord = easy[randomIndex];
        word.setText(firstWord);
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