package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TasksDao {

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);

    @Query("Delete from tasks_table")
    void clearTasks();

    @Query("Select * from tasks_table Order By priority Desc")
    LiveData<List<Task>> selectTasks();
}
