package com.example.abdel.mvvm_demo_tasksapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public abstract class PurchasesDao extends GenericDao<Purchases> {

    @Query("Delete from " + Purchases.TABLE_NAME)
    abstract void clearPurchases();

    @Query("Select * from " + Purchases.TABLE_NAME)
    abstract LiveData<List<Purchases>> selectPurchases();
}
