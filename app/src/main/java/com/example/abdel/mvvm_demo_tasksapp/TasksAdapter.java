package com.example.abdel.mvvm_demo_tasksapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    List<Task> taskList;

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasksViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tasks_single_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task currentTask = taskList.get(position);
        holder.bind(
                currentTask.getTitle(),
                currentTask.getDescription(),
                String.valueOf(currentTask.getPriority())
        );
    }

    Task getTask(int index)
    {
        return index > -1 && index < taskList.size() ? taskList.get(index) : null;
    }

    @Override
    public int getItemCount() {
        if (taskList == null)
            return 0;
        return taskList.size();
    }

    protected class TasksViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleTextView, descriptionTextView, priorityTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.task_title_textView);
            descriptionTextView = itemView.findViewById(R.id.task_description_textView);
            priorityTextView = itemView.findViewById(R.id.task_priority_textView);
        }

        void bind(String title, String description, String priority)
        {
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            priorityTextView.setText(priority);
        }
    }
}
