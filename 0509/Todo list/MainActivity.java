package com.example.todo2;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TodoItem> todoList;
    private TodoAdapter adapter;
    private EditText todoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoList = new ArrayList<>();
        adapter = new TodoAdapter(this, todoList);

        ListView todoListView = findViewById(R.id.todoListView);
        todoEditText = findViewById(R.id.todoEditText);
        Button addButton = findViewById(R.id.addButton);

        todoListView.setAdapter(adapter);

        addButton.setOnClickListener(v -> showAddTodoDialog());
    }

    private void showAddTodoDialog() {
        String todoText = todoEditText.getText().toString().trim();
        if (todoText.isEmpty()) {
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_todo, null);
        
        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        Spinner prioritySpinner = dialogView.findViewById(R.id.prioritySpinner);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);

        titleEditText.setText(todoText);

        builder.setView(dialogView)
                .setTitle("새로운 할 일")
                .setPositiveButton("추가", (dialog, which) -> {
                    TodoItem newItem = new TodoItem(titleEditText.getText().toString());
                    newItem.setPriority(prioritySpinner.getSelectedItemPosition() + 1);
                    newItem.setCategory(categorySpinner.getSelectedItem().toString());
                    todoList.add(newItem);
                    adapter.notifyDataSetChanged();
                    todoEditText.setText("");
                })
                .setNegativeButton("취소", null)
                .show();
    }
}