package com.example.todo2;

public class TodoItem {
    private String title;
    private boolean isCompleted;
    private int priority; // 1: 높음, 2: 중간, 3: 낮음
    private String category;

    public TodoItem(String title) {
        this.title = title;
        this.isCompleted = false;
        this.priority = 2; // 기본값 중간
        this.category = "기본";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
} 