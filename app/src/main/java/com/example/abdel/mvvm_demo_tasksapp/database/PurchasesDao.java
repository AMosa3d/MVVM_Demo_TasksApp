package com.example.abdel.mvvm_demo_tasksapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.abdel.mvvm_demo_tasksapp.Purchases.Purchase;

import java.util.List;

@Dao
public abstract class PurchasesDao extends GenericDao<Purchase> {

    @Query("Delete from " + Purchase.TABLE_NAME)
    abstract void clearPurchases();

    @Query("Select * from " + Purchase.TABLE_NAME)
    abstract LiveData<List<Purchase>> selectPurchases();
}
