package com.example.abdel.mvvm_demo_tasksapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    private AddTaskFragment addTaskFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentById(R.id.add_task_fragment_container);
        if(addTaskFragment == null)
        {
            addTaskFragment = AddTaskFragment.createNewInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.add_task_fragment_container, addTaskFragment);
            transaction.commit();
        }
    }
}
