package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class SentenceActivity extends AppCompatActivity {

    private TextView questionText, scoreText, sentenceDisplay;
    private Button word1, word2, word3, word4;
    private Button clearButton, checkButton, nextButton, backButton;
    
    private String[][] sentences = {
        {"わたし は がくせい です", "나는 학생입니다"},
        {"きょう は いい てんき です", "오늘은 좋은 날씨입니다"},
        {"にほんご を べんきょう します", "일본어를 공부합니다"},
        {"すし を たべます", "초밥을 먹습니다"},
        {"ともだち と はなします", "친구와 이야기합니다"},
        {"がっこう に いきます", "학교에 갑니다"},
        {"ほん を よみます", "책을 읽습니다"},
        {"おんがく を ききます", "음악을 듣습니다"}
    };
    
    private int currentQuestion = 0;
    private int score = 0;
    private String[] currentWords;
    private String correctSentence;
    private List<String> selectedWords = new ArrayList<>();
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);

        initializeViews();
        setupClickListeners();
        generateQuestion();
    }

    private void initializeViews() {
        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        sentenceDisplay = findViewById(R.id.sentenceDisplay);
        word1 = findViewById(R.id.word1);
        word2 = findViewById(R.id.word2);
        word3 = findViewById(R.id.word3);
        word4 = findViewById(R.id.word4);
        clearButton = findViewById(R.id.clearButton);
        checkButton = findViewById(R.id.checkButton);
        nextButton = findViewById(R.id.nextButton);
        backButton = findViewById(R.id.backButton);
        
        updateScore();
    }

    private void setupClickListeners() {
        word1.setOnClickListener(v -> selectWord(word1));
        word2.setOnClickListener(v -> selectWord(word2));
        word3.setOnClickListener(v -> selectWord(word3));
        word4.setOnClickListener(v -> selectWord(word4));
        
        clearButton.setOnClickListener(v -> clearSentence());
        checkButton.setOnClickListener(v -> checkSentence());
        
        nextButton.setOnClickListener(v -> {
            currentQuestion++;
            if (currentQuestion < 8) {
                generateQuestion();
                resetQuestion();
            } else {
                showFinalScore();
            }
        });
        
        backButton.setOnClickListener(v -> finish());
    }

    private void generateQuestion() {
        int questionIndex = random.nextInt(sentences.length);
        correctSentence = sentences[questionIndex][0];
        String meaning = sentences[questionIndex][1];
        
        questionText.setText("다음 문장을 일본어로 만드세요:");
        questionText.append("\n\n\"" + meaning + "\"");
        
        currentWords = correctSentence.split(" ");
        List<String> shuffledWords = new ArrayList<>();
        for (String word : currentWords) {
            shuffledWords.add(word);
        }
        Collections.shuffle(shuffledWords);
        
        Button[] wordButtons = {word1, word2, word3, word4};
        for (int i = 0; i < wordButtons.length; i++) {
            if (i < shuffledWords.size()) {
                wordButtons[i].setText(shuffledWords.get(i));
                wordButtons[i].setVisibility(View.VISIBLE);
            } else {
                wordButtons[i].setVisibility(View.GONE);
            }
        }
    }

    private void selectWord(Button wordButton) {
        if (wordButton.isEnabled()) {
            selectedWords.add(wordButton.getText().toString());
            wordButton.setEnabled(false);
            wordButton.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            updateSentenceDisplay();
        }
    }

    private void updateSentenceDisplay() {
        StringBuilder sentence = new StringBuilder();
        for (int i = 0; i < selectedWords.size(); i++) {
            sentence.append(selectedWords.get(i));
            if (i < selectedWords.size() - 1) {
                sentence.append(" ");
            }
        }
        sentenceDisplay.setText(sentence.toString());
    }

    private void clearSentence() {
        selectedWords.clear();
        sentenceDisplay.setText("");
        Button[] wordButtons = {word1, word2, word3, word4};
        for (Button button : wordButtons) {
            button.setEnabled(true);
            button.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
        }
    }

    private void checkSentence() {
        String userSentence = String.join(" ", selectedWords);
        
        if (userSentence.equals(correctSentence)) {
            score++;
            Toast.makeText(this, "정답입니다! 🎉", Toast.LENGTH_SHORT).show();
            sentenceDisplay.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
        } else {
            Toast.makeText(this, "틀렸습니다. 정답: " + correctSentence, Toast.LENGTH_LONG).show();
            sentenceDisplay.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
        
        updateScore();
        checkButton.setVisibility(View.GONE);
        clearButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
        
        Button[] wordButtons = {word1, word2, word3, word4};
        for (Button button : wordButtons) {
            button.setEnabled(false);
        }
    }

    private void resetQuestion() {
        selectedWords.clear();
        sentenceDisplay.setText("");
        sentenceDisplay.setTextColor(getResources().getColor(android.R.color.black));
        checkButton.setVisibility(View.VISIBLE);
        clearButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.GONE);
    }

    private void updateScore() {
        scoreText.setText("점수: " + score + "/" + (currentQuestion + 1));
    }

    private void showFinalScore() {
        questionText.setText("문장 구성 완료!\n\n최종 점수: " + score + "/8");
        sentenceDisplay.setText("");
        
        Button[] wordButtons = {word1, word2, word3, word4};
        for (Button button : wordButtons) {
            button.setVisibility(View.GONE);
        }
        
        clearButton.setVisibility(View.GONE);
        checkButton.setVisibility(View.GONE);
        nextButton.setText("다시 시작");
        nextButton.setOnClickListener(v -> {
            currentQuestion = 0;
            score = 0;
            for (Button button : wordButtons) {
                button.setVisibility(View.VISIBLE);
            }
            nextButton.setText("다음");
            generateQuestion();
            resetQuestion();
            updateScore();
        });
    }
} 