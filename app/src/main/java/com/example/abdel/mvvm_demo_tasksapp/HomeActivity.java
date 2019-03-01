package com.example.abdel.mvvm_demo_tasksapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.mvvm_demo_tasksapp.Tasks.TasksFragment;

public class HomeActivity extends AppCompatActivity {

    private TasksFragment tasksFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tasksFragment = (TasksFragment) getSupportFragmentManager().findFragmentById(R.id.tasks_fragment_container);
        if(tasksFragment == null)
        {
            tasksFragment = TasksFragment.createNewInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.tasks_fragment_container, tasksFragment);
            transaction.commit();
        }


    }
}
