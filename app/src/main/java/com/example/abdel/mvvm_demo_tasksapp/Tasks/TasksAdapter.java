package com.example.abdel.mvvm_demo_tasksapp.Tasks;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdel.mvvm_demo_tasksapp.R;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private List<Task> tasksList;

    public void setTasksList(List<Task> taskList) {
        this.tasksList = taskList;
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
        Task currentTask = tasksList.get(position);
        holder.bind(
                currentTask.getTitle(),
                currentTask.getDescription(),
                String.valueOf(currentTask.getPriority()),
                currentTask.getDate()
        );
    }

    public Task getTask(int index)
    {
        return index > -1 && index < tasksList.size() ? tasksList.get(index) : null;
    }

    @Override
    public int getItemCount() {
        if (tasksList == null)
            return 0;
        return tasksList.size();
    }

    protected class TasksViewHolder extends RecyclerView.ViewHolder
    {
        TextView titleTextView, descriptionTextView, priorityTextView, dateTextView;

        public TasksViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.task_title_textView);
            descriptionTextView = itemView.findViewById(R.id.task_description_textView);
            priorityTextView = itemView.findViewById(R.id.task_priority_textView);
            dateTextView = itemView.findViewById(R.id.task_date_textView);
        }

        void bind(String title, String description, String priority, String date)
        {
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            priorityTextView.setText(priority);
            if (date != null && !date.equals(""))
                dateTextView.setText("- " + date);
        }
    }
}
