package com.example.abdel.mvvm_demo_tasksapp.Purchases;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.mvvm_demo_tasksapp.R;

public class AddPurchaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_purchase);

        AddPurchaseFragment addPurchaseFragment = (AddPurchaseFragment) getSupportFragmentManager().findFragmentById(R.id.add_purchase_fragment_container);
        if(addPurchaseFragment == null)
        {
            addPurchaseFragment = AddPurchaseFragment.createNewInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.add_purchase_fragment_container, addPurchaseFragment);
            transaction.commit();
        }
    }
}
