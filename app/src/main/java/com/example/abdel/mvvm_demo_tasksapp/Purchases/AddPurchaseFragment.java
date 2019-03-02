package com.example.abdel.mvvm_demo_tasksapp.Purchases;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abdel.mvvm_demo_tasksapp.R;

import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.ADDED_PURCHASE_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.CANCELED_TASK_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.CURRENCY_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.PRICE_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.TITLE_EXTRA_STRING;

public class AddPurchaseFragment extends Fragment {

    EditText titleEditText, priceEditText;
    Spinner currencySpinner;
    Button addBtn, cancelBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_purchase,container,false);

        titleEditText = view.findViewById(R.id.purchase_title_editText);
        priceEditText = view.findViewById(R.id.purchase_price_editText);
        currencySpinner = view.findViewById(R.id.purchase_currency_spinner);
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
                        priceEditText.getText().toString().trim().isEmpty() ||
                        currencySpinner.getSelectedItem() == null)
                {
                    Toast.makeText(getContext(), "Please Fill All The Fields", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();

                intent.putExtra(TITLE_EXTRA_STRING, titleEditText.getText().toString());
                intent.putExtra(PRICE_EXTRA_STRING, Integer.parseInt(priceEditText.getText().toString()));
                intent.putExtra(CURRENCY_EXTRA_STRING, currencySpinner.getSelectedItem().toString());

                getActivity().setResult(ADDED_PURCHASE_RESPONSE, intent);
                getActivity().finish();
            }
        });

        return view;
    }

    public static AddPurchaseFragment createNewInstance()
    {
        return new AddPurchaseFragment();
    }
}
