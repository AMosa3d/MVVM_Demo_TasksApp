package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {Task.class, Purchases.class}, version = TasksDatabase.VERSION)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase instance;
    static final int VERSION = 2;
    private static final String DATABASE_NAME = "Mosa3ed_Assistant";
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table task add column date DATE");
        }
    };

    public abstract TasksDao tasksDao();

    public static synchronized TasksDatabase getInstance(final Context context)
    {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TasksDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .build();

        return instance;
    }
}
