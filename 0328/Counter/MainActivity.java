package com.example.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView counterText;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterText = findViewById(R.id.counterText);
    }

    // 카운터 증가 버튼 클릭 이벤트
    public void increaseCounter(View view) {
        count++;
        updateCounterText();
    }

    // 카운터 감소 버튼 클릭 이벤트
    public void decreaseCounter(View view) {
        count--;
        updateCounterText();
    }

    // 카운터 텍스트 업데이트
    private void updateCounterText() {
        counterText.setText("카운터: " + count);
    }
} 