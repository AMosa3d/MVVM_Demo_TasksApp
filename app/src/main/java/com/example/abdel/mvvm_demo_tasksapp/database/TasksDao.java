package com.example.abdel.mvvm_demo_tasksapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.abdel.mvvm_demo_tasksapp.Tasks.Task;

import java.util.List;

@Dao
public abstract class TasksDao extends GenericDao<Task> {

    @Query("Delete from " + Task.TABLE_NAME)
    abstract void clearTasks();

    @Query("Select * from " + Task.TABLE_NAME + " Order By priority Desc")
    abstract LiveData<List<Task>> selectTasks();
}
