package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = Task.class, version = 1)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase instance;

    public abstract TasksDao tasksDao();

    public static synchronized TasksDatabase getInstance(final Context context)
    {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TasksDatabase.class,"tasks_database").fallbackToDestructiveMigration()
                    .build();

        return instance;
    }
}
