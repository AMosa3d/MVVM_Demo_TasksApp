package com.example.abdel.mvvm_demo_tasksapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Pair;

import java.util.List;

public class TasksRepo {
    private TasksDao tasksDao;
    private LiveData<List<Task>> tasksLiveData;

    private static final String INSERT_TAG = "insert";
    private static final String DELETE_TAG = "delete";
    private static final String UPDATE_TAG = "update";
    private static final String CLEAR_TAG = "Clear";

    public TasksRepo(Application application) {
        TasksDatabase database = TasksDatabase.getInstance(application);
        tasksDao = database.tasksDao();
        tasksLiveData = tasksDao.selectTasks();
    }

    public void insert(Task task)
    {
        new backgroundTasksDatabaseAsyncTask(tasksDao).execute(new Pair<String, Task>(INSERT_TAG,task));
    }

    public void delete(Task task)
    {
        new backgroundTasksDatabaseAsyncTask(tasksDao).execute(new Pair<String, Task>(DELETE_TAG,task));
    }
    public void update(Task task)
    {
        new backgroundTasksDatabaseAsyncTask(tasksDao).execute(new Pair<String, Task>(UPDATE_TAG,task));
    }
    public void clearTasks()
    {
        new backgroundTasksDatabaseAsyncTask(tasksDao).execute(new Pair<String, Task>(CLEAR_TAG,null));
    }

    public LiveData<List<Task>> getTaskLiveData() {
        return tasksLiveData;
    }

    private static class backgroundTasksDatabaseAsyncTask extends AsyncTask<Pair<String,Task>, Void, Void>
    {
        private TasksDao tasksDao;

        public backgroundTasksDatabaseAsyncTask(TasksDao tasksDao) {
            this.tasksDao = tasksDao;
        }

        @Override
        protected Void doInBackground(Pair<String,Task>... pairs) {

            if (pairs[0].first.equals(INSERT_TAG))
                tasksDao.insert(pairs[0].second);
            else if (pairs[0].first.equals(DELETE_TAG))
                tasksDao.delete(pairs[0].second);
            else if (pairs[0].first.equals(UPDATE_TAG))
                tasksDao.update(pairs[0].second);
            else if (pairs[0].first.equals(CLEAR_TAG))
                tasksDao.clearTasks();

            return null;
        }
    }
}
