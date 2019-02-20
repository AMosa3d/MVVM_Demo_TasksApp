package com.example.abdel.mvvm_demo_tasksapp;

import android.os.AsyncTask;

public class AddAsyncTask extends AsyncTask<Void,Void,Void> {

    private TasksDao tasksDao;

    public AddAsyncTask(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        tasksDao.insert(
                new Task(
                "Demo Task", "Demo Description", 1
                )
        );
        return null;
    }
}
