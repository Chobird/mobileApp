package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class HiraganaActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private Button option1, option2, option3, option4;
    private Button nextButton, backButton;
    
    private String[] hiraganaChars = {"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ"};
    private String[] pronunciations = {"a", "ka", "sa", "ta", "na", "ha", "ma", "ya", "ra", "wa"};
    private int currentQuestion = 0;
    private int score = 0;
    private int correctAnswer;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiragana);

        initializeViews();
        setupClickListeners();
        generateQuestion();
    }

    private void initializeViews() {
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        
        updateScore();
    }

    private void setupClickListeners() {
        option1.setOnClickListener(v -> checkAnswer(0));
        option2.setOnClickListener(v -> checkAnswer(1));
        option3.setOnClickListener(v -> checkAnswer(2));
        option4.setOnClickListener(v -> checkAnswer(3));
        
        nextButton.setOnClickListener(v -> {
            currentQuestion++;
            if (currentQuestion < 10) {
                generateQuestion();
                resetButtons();
                nextButton.setVisibility(View.GONE);
            } else {
                showFinalScore();
            }
        });
        
        backButton.setOnClickListener(v -> finish());
    }

    private void generateQuestion() {
        correctAnswer = random.nextInt(4);
        int questionIndex = random.nextInt(hiraganaChars.length);
        
        questionText.setText("다음 히라가나의 발음은?");
        questionText.append("\n\n" + hiraganaChars[questionIndex]);
        
        // 정답을 랜덤한 위치에 배치
        Button[] options = {option1, option2, option3, option4};
        options[correctAnswer].setText(pronunciations[questionIndex]);
        
        // 나머지 선택지를 다른 발음으로 채움
        for (int i = 0; i < 4; i++) {
            if (i != correctAnswer) {
                int wrongIndex;
                do {
                    wrongIndex = random.nextInt(pronunciations.length);
                } while (wrongIndex == questionIndex);
                options[i].setText(pronunciations[wrongIndex]);
            }
        }
    }

    private void checkAnswer(int selectedOption) {
        Button[] options = {option1, option2, option3, option4};
        
        if (selectedOption == correctAnswer) {
            score++;
            options[selectedOption].setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            Toast.makeText(this, "정답입니다! 🎉", Toast.LENGTH_SHORT).show();
        } else {
            options[selectedOption].setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
            options[correctAnswer].setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
            Toast.makeText(this, "틀렸습니다. 정답은 " + options[correctAnswer].getText() + "입니다.", Toast.LENGTH_SHORT).show();
        }
        
        // 모든 버튼 비활성화
        for (Button option : options) {
            option.setEnabled(false);
        }
        
        updateScore();
        nextButton.setVisibility(View.VISIBLE);
    }

    private void resetButtons() {
        Button[] options = {option1, option2, option3, option4};
        for (Button option : options) {
            option.setEnabled(true);
            option.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }
    }

    private void updateScore() {
        scoreText.setText("점수: " + score + "/" + (currentQuestion + 1));
    }

    private void showFinalScore() {
        questionText.setText("학습 완료!\n\n최종 점수: " + score + "/10");
        
        Button[] options = {option1, option2, option3, option4};
        for (Button option : options) {
            option.setVisibility(View.GONE);
        }
        
        nextButton.setText("다시 시작");
        nextButton.setOnClickListener(v -> {
            // 게임 재시작
            currentQuestion = 0;
            score = 0;
            for (Button option : options) {
                option.setVisibility(View.VISIBLE);
            }
            nextButton.setText("다음");
            generateQuestion();
            resetButtons();
            nextButton.setVisibility(View.GONE);
            updateScore();
        });
    }
} 