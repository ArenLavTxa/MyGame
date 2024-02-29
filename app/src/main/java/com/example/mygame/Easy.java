package com.example.mygame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer; // Import CountDownTimer
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Easy extends ChooseTheStage {
    private String[] easy = {"a__ack", "c__rt", "scar__", "li_ht", "_night"};
    private String[] easyans = {"attack", "court", "scarce", "light", "knight"};
    private int currentIndex = 0;
    private TextView word;
    private EditText userInput;
    public int score = 0;
    public int mul = 1;
    private int i = 0;
    private int mistake = 0;
    private TextView timerTextView; // TextView to display timer
    private CountDownTimer countDownTimer; // CountDownTimer object
    private long timeLeftInMillis = 60000; // 1 minute in milliseconds
    private boolean timerRunning; // To keep track of whether timer is running

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        word = findViewById(R.id.word);
        userInput = findViewById(R.id.userInput);
        timerTextView = findViewById(R.id.timerTextView); // Initialize timer TextView

        startTimer(); // Start the timer

        word.setText(easy[0]);
        Button checkButton = findViewById(R.id.accept);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userTypedWord = userInput.getText().toString().toLowerCase();

               if (currentIndex >= easy.length-1|| timerRunning ) {
                    if (userTypedWord.equals(easyans[currentIndex])) {
                        score = score + 100 * mul;
                    }
                    Intent intent = new Intent(Easy.this, EasyScore.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);
                    finish();
                } else if (userTypedWord.equals(easyans[currentIndex])) {
                    currentIndex = (currentIndex + 1) % easy.length;
                    word.setText(easy[currentIndex]);
                    score = score + 100 * mul;
                    mul++;
                    Toast.makeText(Easy.this, "Correct! Score: " + score, Toast.LENGTH_SHORT).show();
                    userInput.setText("");

                } else {
                    currentIndex = (currentIndex + 1) % easy.length;
                    word.setText(easy[currentIndex]);
                    mul = 1;
                    mistake++;
                    Toast.makeText(Easy.this, "Incorrect!", Toast.LENGTH_SHORT).show();
                    userInput.setText("");
                }
                i++;
            }
        });
    }

    // Method to start the timer
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimer(); // Update the timer TextView
            }

            @Override
            public void onFinish() {
                if(timeLeftInMillis==0) {
                    timerRunning = false;
                    // Transition to EasyScore class when timer finishes
                    Intent intent = new Intent(Easy.this, EasyScore.class);
                    intent.putExtra("SCORE", score);
                    startActivity(intent);
                    finish();
                }
            }
        }.start();

        timerRunning = true;
    }

    // Method to update the timer TextView
    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Cancel the timer to avoid memory leaks
        }
    }
}





