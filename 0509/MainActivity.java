package com.example.test03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private SingleTouchView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawView = findViewById(R.id.drawing_view);

        // 색상 버튼 리스너 (Button으로 캐스팅)
        int[] colorBtnIds = {R.id.btn_color_black, R.id.btn_color_red, R.id.btn_color_green, R.id.btn_color_blue, R.id.btn_color_yellow};
        for (int id : colorBtnIds) {
            Button btn = findViewById(id);
            btn.setOnClickListener(v -> {
                String color = v.getTag().toString();
                drawView.setColor(color);
            });
        }

        // 굵기 버튼 리스너 (ImageButton 그대로)
        int[] strokeBtnIds = {R.id.btn_stroke_small, R.id.btn_stroke_medium, R.id.btn_stroke_large};
        for (int id : strokeBtnIds) {
            ImageButton btn = findViewById(id);
            btn.setOnClickListener(v -> {
                float width = Float.parseFloat(v.getTag().toString());
                drawView.setStrokeWidth(width);
            });
        }

        // 지우기 버튼 리스너
        ImageButton btnClear = findViewById(R.id.btn_clear);
        btnClear.setOnClickListener(v -> drawView.clear());
    }
}