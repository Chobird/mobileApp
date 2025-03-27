package com.example.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText eText1;
    private EditText eText2;
    private EditText eText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 뷰 초기화
        eText1 = (EditText) findViewById(R.id.edit1);
        eText2 = (EditText) findViewById(R.id.edit2);
        eText3 = (EditText) findViewById(R.id.edit3);
    }

    public void cal_plus(View v) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) + Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_minus(View v) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) - Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_multiply(View v) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) * Integer.parseInt(s2);
        eText3.setText("" + result);
    }

    public void cal_divide(View v) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        double result = Double.parseDouble(s1) / Double.parseDouble(s2);
        eText3.setText("" + result);
    }
}