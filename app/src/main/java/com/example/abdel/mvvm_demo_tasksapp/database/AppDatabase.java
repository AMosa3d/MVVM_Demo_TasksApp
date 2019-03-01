package com.example.abdel.mvvm_demo_tasksapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.abdel.mvvm_demo_tasksapp.Purchases.Purchase;
import com.example.abdel.mvvm_demo_tasksapp.Tasks.Task;

@Database(entities = {Task.class, Purchase.class}, version = AppDatabase.VERSION)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;
    static final int VERSION = 2;
    private static final String DATABASE_NAME = "Mosa3ed_Assistant";
    static final Migration MIGRATION_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table task add column date DATE");
        }
    };

    public abstract TasksDao tasksDao();
    public abstract PurchasesDao purchasesDao();

    public static synchronized AppDatabase getInstance(final Context context)
    {
        if (instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,DATABASE_NAME).fallbackToDestructiveMigration()
                    .addMigrations(MIGRATION_1_2)
                    .build();

        return instance;
    }
}
