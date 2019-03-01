package com.example.abdel.mvvm_demo_tasksapp.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Pair;

import com.example.abdel.mvvm_demo_tasksapp.Purchases.Purchase;
import com.example.abdel.mvvm_demo_tasksapp.Tasks.Task;

import java.util.List;

public class DatabaseRepo {
    private TasksDao tasksDao;
    private PurchasesDao purchasesDao;
    private LiveData<List<Task>> tasksLiveData;
    private LiveData<List<Purchase>> purchasesLiveData;

    private static final String INSERT_TAG = "insert";
    private static final String DELETE_TAG = "delete";
    private static final String UPDATE_TAG = "update";
    private static final String CLEAR_TAG = "clear";

    public DatabaseRepo(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        tasksDao = database.tasksDao();
        purchasesDao = database.purchasesDao();
        tasksLiveData = tasksDao.selectTasks();
        purchasesLiveData = purchasesDao.selectPurchases();
    }

    public void insert(Model model)
    {
        if (model instanceof Task)
            new backgroundTasksDatabaseAsyncTask<Task>(tasksDao).execute(new Pair<String, Task>(INSERT_TAG, (Task) model));
        else if (model instanceof Purchase)
            new backgroundTasksDatabaseAsyncTask<Purchase>(purchasesDao).execute(new Pair<String, Purchase>(INSERT_TAG, (Purchase) model));
    }

    public void delete(Model model)
    {
        if (model instanceof Task)
            new backgroundTasksDatabaseAsyncTask<Task>(tasksDao).execute(new Pair<String, Task>(DELETE_TAG, (Task) model));
        else if (model instanceof Purchase)
            new backgroundTasksDatabaseAsyncTask<Purchase>(purchasesDao).execute(new Pair<String, Purchase>(DELETE_TAG, (Purchase) model));
    }
    public void update(Model model)
    {
        if (model instanceof Task)
            new backgroundTasksDatabaseAsyncTask<Task>(tasksDao).execute(new Pair<String, Task>(UPDATE_TAG, (Task) model));
        else if (model instanceof Purchase)
            new backgroundTasksDatabaseAsyncTask<Purchase>(purchasesDao).execute(new Pair<String, Purchase>(UPDATE_TAG, (Purchase) model));
    }
    public void clearTasks()
    {
        new backgroundTasksDatabaseAsyncTask<Task>(tasksDao).execute(new Pair<String, Task>(CLEAR_TAG,null));
    }

    public void clearPurchases()
    {
        new backgroundTasksDatabaseAsyncTask<Purchase>(purchasesDao).execute(new Pair<String, Purchase>(CLEAR_TAG,null));
    }

    public LiveData<List<Task>> getTaskLiveData() {
        return tasksLiveData;
    }

    public LiveData<List<Purchase>> getPurchasesLiveData() {
        return purchasesLiveData;
    }

    private static class backgroundTasksDatabaseAsyncTask<Template> extends AsyncTask<Pair<String,Template>, Void, Void>
    {
        private GenericDao currentDao;

        public backgroundTasksDatabaseAsyncTask(GenericDao dao) {
            currentDao = dao;
        }

        @Override
        protected Void doInBackground(Pair<String,Template>... pairs) {

            if (pairs[0].first.equals(INSERT_TAG))
                currentDao.insert(pairs[0].second);
            else if (pairs[0].first.equals(DELETE_TAG))
                currentDao.delete(pairs[0].second);
            else if (pairs[0].first.equals(UPDATE_TAG))
                currentDao.update(pairs[0].second);
            else if (pairs[0].first.equals(CLEAR_TAG))
                if (currentDao instanceof TasksDao)
                    ((TasksDao) currentDao).clearTasks();
                else if (currentDao instanceof PurchasesDao)
                    ((PurchasesDao) currentDao).clearPurchases();


            return null;
        }
    }
}
