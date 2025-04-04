# 안드로이드 계산기 앱 프로젝트

## 프로젝트 개요
이 프로젝트는 기본적인 계산 기능을 제공하는 안드로이드 계산기 앱입니다.

## 주요 기능
- 기본적인 사칙연산 (더하기, 빼기, 곱하기, 나누기)
- 0-9까지의 숫자 입력
- 소수점 계산 지원
- 음수/양수 변환 기능
- 퍼센트 계산 기능
- Clear 기능
- 실시간 결과 표시

## 기술 스택
- 언어: Java
- 개발 환경: Android Studio
- 최소 SDK 버전: Android SDK

## 프로젝트 구조

### MainActivity.java
```java
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

        findViewById(R.id.btnPlusMinus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentNumber.isEmpty()) {
                    if (currentNumber.charAt(0) == '-') {
                        currentNumber = currentNumber.substring(1);
                    } else {
                        currentNumber = "-" + currentNumber;
                    }
                    resultTextView.setText(currentNumber);
                }
            }
        });

        findViewById(R.id.btnPercent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!currentNumber.isEmpty()) {
                    double number = Double.parseDouble(currentNumber);
                    double result = number / 100.0;
                    
                    if (result == (int) result) {
                        currentNumber = String.valueOf((int) result);
                    } else {
                        currentNumber = String.valueOf(result);
                    }
                    resultTextView.setText(currentNumber);
                }
            }
        });

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

        findViewById(R.id.btnEquals).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateResult();
            }
        });
    }

    private void calculateResult() {
        if (!currentNumber.isEmpty() && !operation.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = 0;

            switch (operation) {
                case "+":
                    if (currentNumber.contains("%")) {
                        secondNumber = firstNumber * (secondNumber / 100);
                    }
                    result = firstNumber + secondNumber;
                    break;
                case "-":
                    if (currentNumber.contains("%")) {
                        secondNumber = firstNumber * (secondNumber / 100);
                    }
                    result = firstNumber - secondNumber;
                    break;
                case "×":
                    if (currentNumber.contains("%")) {
                        secondNumber = secondNumber / 100;
                    }
                    result = firstNumber * secondNumber;
                    break;
                case "÷":
                    if (currentNumber.contains("%")) {
                        secondNumber = secondNumber / 100;
                    }
                    if (secondNumber != 0) {
                        result = firstNumber / secondNumber;
                    } else {
                        resultTextView.setText("오류");
                        return;
                    }
                    break;
            }

            if (result == (int) result) {
                currentNumber = String.valueOf((int) result);
            } else {
                currentNumber = String.valueOf(result);
            }

            resultTextView.setText(currentNumber);
            operation = "";
        }
    }
}
```

### activity_main.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/resultTextView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="#EFEFEF"
        android:gravity="end"
        android:padding="16dp"
        android:textSize="30sp"
        android:text="0"
        android:layout_marginBottom="16dp"/>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_weight="1">

        <Button
            android:id="@+id/btnClear"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="C" />

        <Button
            android:id="@+id/btnPlusMinus"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="+/-" />

        <Button
            android:id="@+id/btnPercent"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="%" />

        <Button
            android:id="@+id/btnDivide"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="÷" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_weight="1">

        <Button
            android:id="@+id/btn7"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="7" />

        <Button
            android:id="@+id/btn8"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="8" />

        <Button
            android:id="@+id/btn9"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="9" />

        <Button
            android:id="@+id/btnMultiply"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="×" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_weight="1">

        <Button
            android:id="@+id/btn4"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="4" />

        <Button
            android:id="@+id/btn5"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="5" />

        <Button
            android:id="@+id/btn6"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="6" />

        <Button
            android:id="@+id/btnSubtract"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="-" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_weight="1">

        <Button
            android:id="@+id/btn1"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="1" />

        <Button
            android:id="@+id/btn2"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="2" />

        <Button
            android:id="@+id/btn3"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="3" />

        <Button
            android:id="@+id/btnAdd"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="+" />
    </LinearLayout>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        android:layout_weight="1">

        <Button
            android:id="@+id/btn0"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="2"
            android:textSize="20sp"
            android:text="0" />

        <Button
            android:id="@+id/btnDot"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="." />

        <Button
            android:id="@+id/btnEquals"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:textSize="20sp"
            android:text="=" />
    </LinearLayout>

</LinearLayout>
```

## 주요 기능 설명

### 1. 숫자 입력 처리
- 0-9까지의 숫자 버튼 클릭 이벤트 처리
- 현재 입력된 숫자를 화면에 표시

### 2. 연산자 처리
- 더하기(+), 빼기(-), 곱하기(×), 나누기(÷) 연산 지원
- 연산자 클릭 시 첫 번째 숫자 저장

### 3. 소수점 처리
- 소수점 중복 입력 방지
- 소수점 시작 시 "0." 자동 추가

### 4. 음수/양수 변환
- +/- 버튼으로 현재 숫자의 부호 변경
- 양수는 음수로, 음수는 양수로 변환

### 5. 퍼센트 계산
- % 버튼으로 현재 숫자를 퍼센트로 변환
- 덧셈/뺄셈: 첫 번째 숫자의 퍼센트로 계산
- 곱셈/나눗셈: 퍼센트를 소수로 변환하여 계산

### 6. 결과 계산
- 등호(=) 버튼 클릭 시 계산 수행
- 0으로 나누기 시 오류 처리
- 정수 결과일 경우 소수점 제거

### 7. 초기화 기능
- Clear(C) 버튼으로 모든 입력값 초기화
- 계산기 상태를 처음 상태로 리셋

## 사용자 인터페이스
1. 결과 표시창
   - 크기: 30sp
   - 배경색: #EFEFEF
   - 우측 정렬

2. 버튼 구성
   - 숫자 버튼 (0-9)
   - 연산자 버튼 (+, -, ×, ÷)
   - 기능 버튼 (C, +/-, %, ., =)
   - 버튼 크기: 동일한 크기로 배치

## 오류 처리
1. 0으로 나누기 시도 시 "오류" 메시지 표시
2. 소수점 중복 입력 방지
3. 잘못된 연산자 입력 방지

## 설치 및 실행 방법
1. Android Studio에서 프로젝트 열기
2. 프로젝트 빌드
3. 에뮬레이터 또는 실제 안드로이드 기기에서 실행

## 최소 요구사항
- Android SDK 버전: 최소 API 레벨 지원
- Android Studio 최신 버전
- JDK 설치

## 라이선스
이 프로젝트는 오픈 소스로 제공됩니다.
