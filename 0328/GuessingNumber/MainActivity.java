package com.example.guessingnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
    private EditText guessInput;
    private TextView resultText;
    private Random random;
    private int targetNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guessInput = findViewById(R.id.guessInput);
        resultText = findViewById(R.id.resultText);
        random = new Random();
        
        // 1-100 사이의 랜덤 숫자 생성
        targetNumber = random.nextInt(100) + 1;
    }

    // 숫자 확인 버튼 클릭 이벤트
    public void checkGuess(View view) {
        String input = guessInput.getText().toString();
        
        if (input.isEmpty()) {
            Toast.makeText(this, "숫자를 입력해주세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        int guessNumber = Integer.parseInt(input);
        
        if (guessNumber < 1 || guessNumber > 100) {
            Toast.makeText(this, "1-100 사이의 숫자를 입력해주세요!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guessNumber < targetNumber) {
            resultText.setText("Up!!");
        } else if (guessNumber > targetNumber) {
            resultText.setText("Down!!");
        } else {
            resultText.setText("정답입니다!");
            // 새로운 숫자 생성
            targetNumber = random.nextInt(100) + 1;
        }
        
        // 입력 필드 초기화
        guessInput.setText("");
    }
} 