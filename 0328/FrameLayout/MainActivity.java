package com.example.framelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.view1);
        tv2 = findViewById(R.id.view2);
        tv3 = findViewById(R.id.view3);
    }

    public void onClick(View view) {
        // 모든 뷰를 먼저 숨김
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);

        // 클릭된 버튼에 따라 해당하는 뷰만 보이게 함
        switch (view.getId()) {
            case R.id.button1:
                tv1.setVisibility(View.VISIBLE);
                break;
            case R.id.button2:
                tv2.setVisibility(View.VISIBLE);
                break;
            case R.id.button3:
                tv3.setVisibility(View.VISIBLE);
                break;
        }
    }
} 