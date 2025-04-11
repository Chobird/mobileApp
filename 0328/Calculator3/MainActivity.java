package com.example.calculator3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText number1, number2;
    private TextView result;
    private Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소 초기화
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        result = findViewById(R.id.result);
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);

        // 버튼 클릭 리스너 설정
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('×');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('÷');
            }
        });
    }

    private void calculate(char operator) {
        try {
            double num1 = Double.parseDouble(number1.getText().toString());
            double num2 = Double.parseDouble(number2.getText().toString());
            double resultValue = 0;

            switch (operator) {
                case '+':
                    resultValue = num1 + num2;
                    break;
                case '-':
                    resultValue = num1 - num2;
                    break;
                case '×':
                    resultValue = num1 * num2;
                    break;
                case '÷':
                    if (num2 != 0) {
                        resultValue = num1 / num2;
                    } else {
                        result.setText("0으로 나눌 수 없습니다");
                        return;
                    }
                    break;
            }

            // 결과가 정수인 경우 소수점 제거
            if (resultValue == (int) resultValue) {
                result.setText(String.format("%d", (int) resultValue));
            } else {
                result.setText(String.format("%.2f", resultValue));
            }

        } catch (NumberFormatException e) {
            result.setText("올바른 숫자를 입력하세요");
        }
    }
} 