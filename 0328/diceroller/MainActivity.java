package com.example.diceroller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
    private ImageView diceImage;
    private Random random = new Random();
    private final int[] diceArray = {
        R.drawable.dice1,
        R.drawable.dice2,
        R.drawable.dice3,
        R.drawable.dice4,
        R.drawable.dice5,
        R.drawable.dice6
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceImage = findViewById(R.id.diceImage);
    }

    // 주사위 굴리기 버튼 클릭 이벤트
    public void rollDice(View view) {
        // 1-6 사이의 랜덤 숫자 생성
        int randomNumber = random.nextInt(6);
        // 해당하는 주사위 이미지로 변경
        diceImage.setImageResource(diceArray[randomNumber]);
    }
} 