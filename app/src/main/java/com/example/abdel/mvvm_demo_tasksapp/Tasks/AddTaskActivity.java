package com.example.abdel.mvvm_demo_tasksapp.Tasks;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.abdel.mvvm_demo_tasksapp.R;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        AddTaskFragment addTaskFragment = (AddTaskFragment) getSupportFragmentManager().findFragmentById(R.id.add_task_fragment_container);
        if(addTaskFragment == null)
        {
            addTaskFragment = AddTaskFragment.createNewInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.add_task_fragment_container, addTaskFragment);
            transaction.commit();
        }
    }
}
