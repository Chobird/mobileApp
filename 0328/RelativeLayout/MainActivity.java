package com.example.relativelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText addressInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressInput = findViewById(R.id.input);
        Button confirmButton = findViewById(R.id.confirm);
        Button cancelButton = findViewById(R.id.cancel);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressInput.getText().toString();
                if (address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "주소를 입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "입력된 주소: " + address, Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInput.setText("");
                Toast.makeText(MainActivity.this, "입력이 취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
} 