package com.example.linearlayout3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText toEditText;
    private EditText subjectEditText;
    private EditText messageEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toEditText = findViewById(R.id.toEditText);
        subjectEditText = findViewById(R.id.subjectEditText);
        messageEditText = findViewById(R.id.messageEditText);
    }

    // 전송 버튼 클릭 이벤트
    public void sendMessage(View view) {
        String to = toEditText.getText().toString();
        String subject = subjectEditText.getText().toString();
        String message = messageEditText.getText().toString();

        // 입력값 검증
        if (to.isEmpty() || subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 메시지 전송 성공 알림
        Toast.makeText(this, "메시지가 전송되었습니다.", Toast.LENGTH_SHORT).show();
        
        // 입력 필드 초기화
        toEditText.setText("");
        subjectEditText.setText("");
        messageEditText.setText("");
    }
} 