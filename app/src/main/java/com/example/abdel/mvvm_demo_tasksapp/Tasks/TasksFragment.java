package com.example.abdel.mvvm_demo_tasksapp.Tasks;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.abdel.mvvm_demo_tasksapp.R;

import java.util.List;

import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.ADDED_TASK_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.ADD_TASK_REQUEST;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.CANCELED_TASK_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.DATE_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.DESCRIPTION_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.PRIORITY_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.TASK_PRIORITY_DEFAULT_VALUE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.TITLE_EXTRA_STRING;

public class TasksFragment extends Fragment {

    private TasksViewModel tasksViewModel;
    private RecyclerView tasksRecyclerView;
    private FloatingActionButton addFAB;
    private TasksAdapter tasksAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tasksAdapter = new TasksAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        addFAB = view.findViewById(R.id.add_FAB);
        tasksRecyclerView = view.findViewById(R.id.tasks_recyclerView);

        tasksRecyclerView.setAdapter(tasksAdapter);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        tasksViewModel = ViewModelProviders.of(this).get(TasksViewModel.class);
        tasksViewModel.getTasksLiveData().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                tasksAdapter.setTasksList(tasks);
            }
        });

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivityForResult(intent, ADD_TASK_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Task currentTask = tasksAdapter.getTask(viewHolder.getAdapterPosition());
                if (currentTask != null)
                    tasksViewModel.delete(currentTask);
            }
        }).attachToRecyclerView(tasksRecyclerView);

        return view;
    }

    public static TasksFragment createNewInstance()
    {
        return new TasksFragment();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_TASK_REQUEST && resultCode == ADDED_TASK_RESPONSE)
        {
            Toast.makeText(getContext(), getString(R.string.operation_task_add_successful), Toast.LENGTH_LONG).show();

            tasksViewModel.insert(
                    new Task(
                            data.getStringExtra(TITLE_EXTRA_STRING),
                            data.getStringExtra(DESCRIPTION_EXTRA_STRING),
                            data.getIntExtra(PRIORITY_EXTRA_STRING, TASK_PRIORITY_DEFAULT_VALUE),
                            data.getStringExtra(DATE_EXTRA_STRING)
                    )
            );
        }
        else if (requestCode == ADD_TASK_REQUEST && resultCode == CANCELED_TASK_RESPONSE)
            Toast.makeText(getContext(), getString(R.string.operation_canceled), Toast.LENGTH_LONG).show();
    }
}
