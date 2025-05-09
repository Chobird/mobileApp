package com.example.todo2;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.ArrayList;

public class TodoAdapter extends ArrayAdapter<TodoItem> {
    private Context context;
    private ArrayList<TodoItem> todoList;

    public TodoAdapter(Context context, ArrayList<TodoItem> todoList) {
        super(context, 0, todoList);
        this.context = context;
        this.todoList = todoList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_item, parent, false);
        }

        TodoItem currentItem = todoList.get(position);

        TextView titleTextView = convertView.findViewById(R.id.titleTextView);
        CheckBox checkBox = convertView.findViewById(R.id.checkBox);
        ImageButton editButton = convertView.findViewById(R.id.editButton);
        ImageButton deleteButton = convertView.findViewById(R.id.deleteButton);
        TextView priorityTextView = convertView.findViewById(R.id.priorityTextView);
        TextView categoryTextView = convertView.findViewById(R.id.categoryTextView);

        titleTextView.setText(currentItem.getTitle());
        checkBox.setChecked(currentItem.isCompleted());
        
        // 우선순위 표시
        String priorityText = "우선순위: ";
        switch (currentItem.getPriority()) {
            case 1:
                priorityText += "높음";
                break;
            case 2:
                priorityText += "중간";
                break;
            case 3:
                priorityText += "낮음";
                break;
        }
        priorityTextView.setText(priorityText);
        
        // 카테고리 표시
        categoryTextView.setText("카테고리: " + currentItem.getCategory());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            currentItem.setCompleted(isChecked);
        });

        editButton.setOnClickListener(v -> showEditDialog(currentItem));
        deleteButton.setOnClickListener(v -> showDeleteDialog(position));

        return convertView;
    }

    private void showEditDialog(TodoItem item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_todo, null);
        
        EditText titleEditText = dialogView.findViewById(R.id.titleEditText);
        Spinner prioritySpinner = dialogView.findViewById(R.id.prioritySpinner);
        Spinner categorySpinner = dialogView.findViewById(R.id.categorySpinner);

        titleEditText.setText(item.getTitle());
        prioritySpinner.setSelection(item.getPriority() - 1);
        
        // 카테고리 Spinner 설정
        String[] categories = context.getResources().getStringArray(R.array.category_items);
        for (int i = 0; i < categories.length; i++) {
            if (categories[i].equals(item.getCategory())) {
                categorySpinner.setSelection(i);
                break;
            }
        }

        builder.setView(dialogView)
                .setTitle("할 일 수정")
                .setPositiveButton("저장", (dialog, which) -> {
                    item.setTitle(titleEditText.getText().toString());
                    item.setPriority(prioritySpinner.getSelectedItemPosition() + 1);
                    item.setCategory(categorySpinner.getSelectedItem().toString());
                    notifyDataSetChanged();
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private void showDeleteDialog(int position) {
        new AlertDialog.Builder(context)
                .setTitle("할 일 삭제")
                .setMessage("정말로 이 할 일을 삭제하시겠습니까?")
                .setPositiveButton("삭제", (dialog, which) -> {
                    todoList.remove(position);
                    notifyDataSetChanged();
                })
                .setNegativeButton("취소", null)
                .show();
    }
} 