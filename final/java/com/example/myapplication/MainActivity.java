package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView progressText;
    private CardView hiraganaCard, katakanaCard, vocabularyCard, sentenceCard, pronunciationCard, reviewCard;
    
    // 학습 진도 데이터
    private int totalQuestions = 100;
    private int completedQuestions = 45;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setupClickListeners();
        updateProgress();
    }

    private void initializeViews() {
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);
        hiraganaCard = findViewById(R.id.hiraganaCard);
        katakanaCard = findViewById(R.id.katakanaCard);
        vocabularyCard = findViewById(R.id.vocabularyCard);
        sentenceCard = findViewById(R.id.sentenceCard);
        pronunciationCard = findViewById(R.id.pronunciationCard);
        reviewCard = findViewById(R.id.reviewCard);
    }

    private void setupClickListeners() {
        hiraganaCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, HiraganaActivity.class);
            startActivity(intent);
        });

        katakanaCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, KatakanaActivity.class);
            startActivity(intent);
        });

        vocabularyCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, VocabularyActivity.class);
            startActivity(intent);
        });

        sentenceCard.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SentenceActivity.class);
            startActivity(intent);
        });

        pronunciationCard.setOnClickListener(v -> {
            startLearningActivity("발음 연습", "pronunciation");
        });

        reviewCard.setOnClickListener(v -> {
            startLearningActivity("복습 모드", "review");
        });
    }

    private void startLearningActivity(String title, String type) {
        // 학습 액티비티로 이동하는 코드
        // 현재는 토스트 메시지로 대체
        Toast.makeText(this, title + " 모드를 시작합니다!", Toast.LENGTH_SHORT).show();
        
        // 실제 구현시에는 다음과 같이 새로운 액티비티를 시작할 수 있습니다:
        // Intent intent = new Intent(this, LearningActivity.class);
        // intent.putExtra("learning_type", type);
        // intent.putExtra("title", title);
        // startActivity(intent);
        
        // 진도 업데이트 (시뮬레이션)
        if (completedQuestions < totalQuestions) {
            completedQuestions += 5;
            updateProgress();
        }
    }

    private void updateProgress() {
        int progressPercentage = (completedQuestions * 100) / totalQuestions;
        progressBar.setProgress(progressPercentage);
        progressText.setText(completedQuestions + "/" + totalQuestions + " 문항 완료");
    }

    // 학습 데이터 및 유틸리티 메소드들
    public static class LearningData {
        
        public static String[] getHiraganaData() {
            return new String[]{"あ", "か", "さ", "た", "な", "は", "ま", "や", "ら", "わ"};
        }
        
        public static String[] getKatakanaData() {
            return new String[]{"ア", "カ", "サ", "タ", "ナ", "ハ", "マ", "ヤ", "ラ", "ワ"};
        }
        
        public static String[][] getVocabularyData() {
            return new String[][]{
                {"こんにちは", "안녕하세요"},
                {"ありがとう", "고마워요"},
                {"すみません", "죄송합니다"},
                {"はじめまして", "처음 뵙겠습니다"},
                {"さようなら", "안녕히 가세요"},
                {"おはよう", "좋은 아침"},
                {"こんばんは", "좋은 저녁"},
                {"おやすみ", "안녕히 주무세요"},
                {"げんき", "건강/좋다"},
                {"がっこう", "학교"}
            };
        }
        
        public static String[] getSampleSentences() {
            return new String[]{
                "わたしは がくせい です。(나는 학생입니다.)",
                "きょうは いい てんき です。(오늘은 좋은 날씨입니다.)",
                "にほんご を べんきょう します。(일본어를 공부합니다.)",
                "すし を たべます。(초밥을 먹습니다.)",
                "ともだち と はなします。(친구와 이야기합니다.)"
            };
        }
    }
}