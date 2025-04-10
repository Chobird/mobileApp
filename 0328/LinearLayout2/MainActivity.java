package com.example.linearlayout2;

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

    // 버튼 클릭 이벤트
    public void showMessage(View view) {
        Toast.makeText(this, "버튼을 클릭했습니다.", Toast.LENGTH_SHORT).show();
    }
} 