package com.example.tablelayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText addressInput;
    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addressInput = findViewById(R.id.addressInput);
        nameInput = findViewById(R.id.nameInput);
        Button saveButton = findViewById(R.id.saveButton);
        Button cancelButton = findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = addressInput.getText().toString();
                String name = nameInput.getText().toString();
                String message = "주소: " + address + "\n이름: " + name;
                Toast.makeText(MainActivity.this, "저장되었습니다\n" + message, Toast.LENGTH_LONG).show();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addressInput.setText("");
                nameInput.setText("");
                Toast.makeText(MainActivity.this, "입력이 취소되었습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
} 