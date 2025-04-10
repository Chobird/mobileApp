package com.example.linearlayout1;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 버튼 1 클릭 이벤트
    public void showToast1(View view) {
        Toast.makeText(this, "첫 번째 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }

    // 버튼 2 클릭 이벤트
    public void showToast2(View view) {
        Toast.makeText(this, "두 번째 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }

    // 버튼 3 클릭 이벤트
    public void showToast3(View view) {
        Toast.makeText(this, "세 번째 버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }
} 