package com.example.codelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 수직 방향의 LinearLayout 생성
        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);

        // 첫 번째 버튼 생성 및 설정
        Button b1 = new Button(this);
        b1.setText("첫번째 버튼");
        container.addView(b1);

        // 두 번째 버튼 생성 및 설정
        Button b2 = new Button(this);
        b2.setText("두번째 버튼");
        container.addView(b2);

        // 버튼 클릭 이벤트 설정
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b1.setText("첫번째 버튼 클릭됨");
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b2.setText("두번째 버튼 클릭됨");
            }
        });

        // 생성한 레이아웃을 화면에 설정
        setContentView(container);
    }
} 