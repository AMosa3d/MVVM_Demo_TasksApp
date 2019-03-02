package com.example.abdel.mvvm_demo_tasksapp.Purchases;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.abdel.mvvm_demo_tasksapp.database.DatabaseRepo;

import java.util.List;

public class PurchasesViewModel extends AndroidViewModel {

    private DatabaseRepo repo;
    private LiveData<List<Purchase>> purchasesLiveData;

    public PurchasesViewModel(@NonNull Application application) {
        super(application);
        repo = new DatabaseRepo(application);
        purchasesLiveData = repo.getPurchasesLiveData();
    }

    public void insert(Purchase purchase)
    {
        repo.insert(purchase);
    }

    public void delete(Purchase purchase)
    {
        repo.delete(purchase);
    }

    public void update(Purchase purchase)
    {
        repo.update(purchase);
    }

    public void clearPurchases()
    {
        repo.clearPurchases();
    }

    public LiveData<List<Purchase>> getPurchasesLiveData() {
        return purchasesLiveData;
    }
}
