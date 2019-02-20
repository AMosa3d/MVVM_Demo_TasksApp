package com.example.abdel.mvvm_demo_tasksapp;

import android.os.AsyncTask;

public class DeleteAsyncTask extends AsyncTask<Task,Void,Void> {

    private TasksDao tasksDao;

    public DeleteAsyncTask(TasksDao tasksDao) {
        this.tasksDao = tasksDao;
    }

    @Override
    protected Void doInBackground(Task... voids) {
        tasksDao.delete(
                voids[0]
        );
        return null;
    }
}
