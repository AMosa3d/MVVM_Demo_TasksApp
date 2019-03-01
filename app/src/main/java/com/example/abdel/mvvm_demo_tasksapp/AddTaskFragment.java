package com.example.abdel.mvvm_demo_tasksapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import static com.example.abdel.mvvm_demo_tasksapp.Constants.ADDED_TASK_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Constants.CANCELED_TASK_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Constants.DATE_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Constants.DESCRIPTION_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Constants.PRIORITY_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Constants.TITLE_EXTRA_STRING;

public class AddTaskFragment extends Fragment {

    EditText titleEditText, descriptionEditText;
    NumberPickerCustomUI priorityNumberPicker;
    Button addBtn, cancelBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);

        titleEditText = view.findViewById(R.id.title_editText);
        descriptionEditText = view.findViewById(R.id.description_editText);
        priorityNumberPicker = view.findViewById(R.id.priority_numberPicker);
        addBtn = view.findViewById(R.id.add_btn);
        cancelBtn = view.findViewById(R.id.cancel_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setResult(CANCELED_TASK_RESPONSE);
                getActivity().finish();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleEditText.getText().toString().trim().isEmpty() ||
                        descriptionEditText.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(getContext(), "Please Fill All The Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();

                intent.putExtra(TITLE_EXTRA_STRING, titleEditText.getText().toString());
                intent.putExtra(DESCRIPTION_EXTRA_STRING, descriptionEditText.getText().toString());
                intent.putExtra(PRIORITY_EXTRA_STRING, priorityNumberPicker.getValue());
                intent.putExtra(DATE_EXTRA_STRING, DateFormat.format("dd/mm/yyyy", new Date()));

                getActivity().setResult(ADDED_TASK_RESPONSE, intent);
                getActivity().finish();
            }
        });

        return view;
    }

    public static AddTaskFragment createNewInstance()
    {
        return new AddTaskFragment();
    }
}
