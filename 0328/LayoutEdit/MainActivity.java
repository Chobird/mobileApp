package com.example.layoutedit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageView androidImage;
    private Button letsGoButton;
    private boolean isImageLarge = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        androidImage = findViewById(R.id.androidImage);
        letsGoButton = findViewById(R.id.letsGoButton);

        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isImageLarge) {
                    // 이미지를 원래 크기로 되돌림
                    androidImage.getLayoutParams().width = 200;
                    androidImage.getLayoutParams().height = 200;
                } else {
                    // 이미지를 더 크게 만듦
                    androidImage.getLayoutParams().width = 400;
                    androidImage.getLayoutParams().height = 400;
                }
                androidImage.requestLayout();
                isImageLarge = !isImageLarge;
            }
        });
    }
} 