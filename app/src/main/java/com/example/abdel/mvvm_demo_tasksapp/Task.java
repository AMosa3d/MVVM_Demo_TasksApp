package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = Task.TABLE_NAME)
public class Task {

    @Ignore
    public static final String TABLE_NAME = "tasks_table";

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "priority")
    private int priority;

    @ColumnInfo(name = "date")
    private String date;

    public Task(String title, String description, int priority, String date) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
