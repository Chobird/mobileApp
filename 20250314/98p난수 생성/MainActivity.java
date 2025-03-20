package com.example.randomnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView textViewrandomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    textViewrandomNumber = findViewById(R.id.textViewrandomNumber);

        }

        public  void  generateRandomNumber(View vlew){
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        textViewrandomNumber.setText("난수 : "+ randomNumber);
    }
}