package com.example.abdel.mvvm_demo_tasksapp.Tasks;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.abdel.mvvm_demo_tasksapp.database.DatabaseRepo;

import java.util.List;

public class TasksViewModel extends AndroidViewModel {

    private DatabaseRepo repo;
    private LiveData<List<Task>> tasksLiveData;

    public TasksViewModel(@NonNull Application application) {
        super(application);
        repo = new DatabaseRepo(application);
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
