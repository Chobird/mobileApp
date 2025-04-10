package com.example.textview01;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView1);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);

        // 첫 번째 TextView - 텍스트 내용 변경
        tv1.setText("자바 코드로 변경하였습니다.");

        // 두 번째 TextView - 색상과 크기 변경
        tv2.setTextColor(Color.BLUE);
        tv2.setTextSize(60);

        // 세 번째 TextView - 크기와 폰트 스타일 변경
        tv3.setTextSize(60);
        tv3.setTypeface(Typeface.SERIF, Typeface.ITALIC);
    }
} 