package com.example.caculator01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewNumber = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        resultTextView = findViewById(R.id.resultTextView);
        
        // 숫자 버튼 리스너 설정
        setNumberButtonClickListeners();
        // 연산자 버튼 리스너 설정
        setOperatorButtonClickListeners();
    }

    private void setNumberButtonClickListeners() {
        int[] numberIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                          R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                          R.id.button8, R.id.button9};
        
        for (int id : numberIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                if (isNewNumber) {
                    currentNumber = ((Button) v).getText().toString();
                    isNewNumber = false;
                } else {
                    currentNumber += ((Button) v).getText().toString();
                }
                resultTextView.setText(currentNumber);
            });
        }
    }

    private void setOperatorButtonClickListeners() {
        // 연산자 버튼 설정
        findViewById(R.id.buttonPlus).setOnClickListener(v -> setOperator("+"));
        findViewById(R.id.buttonMinus).setOnClickListener(v -> setOperator("-"));
        findViewById(R.id.buttonMultiply).setOnClickListener(v -> setOperator("*"));
        findViewById(R.id.buttonDivide).setOnClickListener(v -> setOperator("/"));
        
        // 등호 버튼
        findViewById(R.id.buttonEquals).setOnClickListener(v -> calculateResult());
        
        // AC 버튼
        findViewById(R.id.buttonAC).setOnClickListener(v -> {
            currentNumber = "";
            operator = "";
            firstNumber = 0;
            isNewNumber = true;
            resultTextView.setText("0");
        });
    }

    private void setOperator(String newOperator) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
        }
        operator = newOperator;
        isNewNumber = true;
    }

    private void calculateResult() {
        if (operator.isEmpty() || currentNumber.isEmpty()) return;
        
        double secondNumber = Double.parseDouble(currentNumber);
        double result = 0;
        
        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    resultTextView.setText("Error");
                    return;
                }
                break;
        }
        
        currentNumber = String.valueOf(result);
        resultTextView.setText(currentNumber);
        operator = "";
        isNewNumber = true;
    }
}