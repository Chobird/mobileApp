package com.example.caculator01;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private String currentNumber = "";
    private double firstNumber = 0;
    private String operation = "";
    private boolean isOperationClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);

        setNumberButtonListeners();
        setOperationButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String buttonText = button.getText().toString();

                if (isOperationClicked) {
                    currentNumber = buttonText;
                    isOperationClicked = false;
                } else {
                    currentNumber += buttonText;
                }

                resultTextView.setText(currentNumber);
            }
        };

        for (int id : numberButtonIds) {
            findViewById(id).setOnClickListener(numberClickListener);
        }

        // 소수점(Dot) 버튼 리스너
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOperationClicked) {
                    currentNumber = "0.";
                    isOperationClicked = false;
                } else if (!currentNumber.contains(".")) {
                    if (currentNumber.isEmpty()) {
                        currentNumber = "0.";
                    } else {
                        currentNumber += ".";
                    }
                }
                resultTextView.setText(currentNumber);
            }
        });
    }

    private void setOperationButtonListeners() {
        // 더하기, 빼기, 곱하기, 나누기 버튼 리스너
        View.OnClickListener operationClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                if (!currentNumber.isEmpty()) {
                    firstNumber = Double.parseDouble(currentNumber);
                }

                operation = button.getText().toString();
                isOperationClicked = true;
            }
        };

        findViewById(R.id.btnAdd).setOnClickListener(operationClickListener);
        findViewById(R.id.btnSubtract).setOnClickListener(operationClickListener);
        findViewById(R.id.btnMultiply).setOnClickListener(operationClickListener);
        findViewById(R.id.btnDivide).setOnClickListener(operationClickListener);

        // C(Clear) 버튼 리스너
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumber = "";
                firstNumber = 0;
                operation = "";
                isOperationClicked = false;
                resultTextView.setText("0");
            }
        });

        // 등호(=) 버튼 리스너
        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentNumber.isEmpty() && !operation.isEmpty()) {
                    double secondNumber = Double.parseDouble(currentNumber);
                    double result = 0;

                    switch (operation) {
                        case "+":
                            result = firstNumber + secondNumber;
                            break;
                        case "-":
                            result = firstNumber - secondNumber;
                            break;
                        case "×":
                            result = firstNumber * secondNumber;
                            break;
                        case "÷":
                            if (secondNumber != 0) {
                                result = firstNumber / secondNumber;
                            } else {
                                resultTextView.setText("오류");
                                return;
                            }
                            break;
                    }

                    // 결과가 정수인 경우 소수점 없이 표시
                    if (result == (int) result) {
                        currentNumber = String.valueOf((int) result);
                    } else {
                        currentNumber = String.valueOf(result);
                    }

                    resultTextView.setText(currentNumber);
                    operation = "";
                }
            }
        });
    }
}