package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class VocabularyActivity extends AppCompatActivity {

    private TextView questionText, scoreText;
    private Button option1, option2, option3, option4;
    private Button nextButton, backButton;
    
    private String[][] vocabulary = {
        {"こんにちは", "안녕하세요"},
        {"ありがとう", "고마워요"},
        {"すみません", "죄송합니다"},
        {"はじめまして", "처음 뵙겠습니다"},
        {"さようなら", "안녕히 가세요"},
        {"おはよう", "좋은 아침"},
        {"こんばんは", "좋은 저녁"},
        {"おやすみ", "안녕히 주무세요"},
        {"げんき", "건강하다/좋다"},
        {"がっこう", "학교"},
        {"せんせい", "선생님"},
        {"がくせい", "학생"},
        {"ともだち", "친구"},
        {"かぞく", "가족"},
        {"いえ", "집"}
    };
    
    private int currentQuestion = 0;
    private int score = 0;
    private int correctAnswer;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);

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
        int questionIndex = random.nextInt(vocabulary.length);
        
        questionText.setText("다음 일본어 단어의 뜻은?");
        questionText.append("\n\n" + vocabulary[questionIndex][0]);
        
        Button[] options = {option1, option2, option3, option4};
        options[correctAnswer].setText(vocabulary[questionIndex][1]);
        
        for (int i = 0; i < 4; i++) {
            if (i != correctAnswer) {
                int wrongIndex;
                do {
                    wrongIndex = random.nextInt(vocabulary.length);
                } while (wrongIndex == questionIndex);
                options[i].setText(vocabulary[wrongIndex][1]);
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
            option.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }
    }

    private void updateScore() {
        scoreText.setText("점수: " + score + "/" + (currentQuestion + 1));
    }

    private void showFinalScore() {
        questionText.setText("단어 학습 완료!\n\n최종 점수: " + score + "/10");
        
        Button[] options = {option1, option2, option3, option4};
        for (Button option : options) {
            option.setVisibility(View.GONE);
        }
        
        nextButton.setText("다시 시작");
        nextButton.setOnClickListener(v -> {
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