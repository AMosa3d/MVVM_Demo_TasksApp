package com.example.abdel.mvvm_demo_tasksapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private TasksRepo repo;
    private LiveData<List<Task>> tasksLiveData;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        repo = new TasksRepo(application);
        tasksLiveData = repo.getTaskLiveData();
    }

    public void insert(Task task)
    {
        repo.insert(task);
    }

    public void delete(Task task)
    {
        repo.delete(task);
    }
    public void update(Task task)
    {
        repo.update(task);
    }
    public void clearTasks()
    {
        repo.clearTasks();
    }

    public LiveData<List<Task>> getTasksLiveData() {
        return tasksLiveData;
    }
}
