package com.example.abdel.mvvm_demo_tasksapp.Purchases;

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
import com.example.abdel.mvvm_demo_tasksapp.Tasks.AddTaskActivity;

import java.util.List;

import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.ADDED_PURCHASE_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.ADD_PURCHASE_REQUEST;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.CANCELED_PURCHASE_RESPONSE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.CURRENCY_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.PRICE_EXTRA_STRING;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.PURCHASE_PRICE_DEFAULT_VALUE;
import static com.example.abdel.mvvm_demo_tasksapp.Utils.Constants.TITLE_EXTRA_STRING;

public class PurchaseFragment extends Fragment {

    private PurchasesViewModel purchasesViewModel;
    private RecyclerView purchasesRecyclerView;
    private FloatingActionButton addFAB;
    private PurchasesAdapter purchasesAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        purchasesAdapter = new PurchasesAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchases, container, false);

        addFAB = view.findViewById(R.id.add_FAB);
        purchasesRecyclerView = view.findViewById(R.id.purchases_recyclerView);

        purchasesRecyclerView.setAdapter(purchasesAdapter);
        purchasesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        purchasesViewModel = ViewModelProviders.of(this).get(PurchasesViewModel.class);
        purchasesViewModel.getPurchasesLiveData().observe(this, new Observer<List<Purchase>>(){
            @Override
            public void onChanged(@Nullable List<Purchase> purchases) {
                purchasesAdapter.setPurchasesList(purchases);
            }
        });

        addFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddTaskActivity.class);
                startActivityForResult(intent, ADD_PURCHASE_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Purchase currentPurchase = purchasesAdapter.getPurchase(viewHolder.getAdapterPosition());
                if(currentPurchase != null)
                    purchasesViewModel.delete(currentPurchase);
            }
        }).attachToRecyclerView(purchasesRecyclerView);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_PURCHASE_REQUEST && resultCode == ADDED_PURCHASE_RESPONSE)
        {
            Toast.makeText(getContext(), getString(R.string.operation_purchase_add_successful), Toast.LENGTH_LONG).show();

            purchasesViewModel.insert(
                    new Purchase(
                            data.getStringExtra(TITLE_EXTRA_STRING),
                            data.getIntExtra(PRICE_EXTRA_STRING, PURCHASE_PRICE_DEFAULT_VALUE),
                            data.getStringExtra(CURRENCY_EXTRA_STRING)
                    )
            );
        }
        else if (requestCode == ADD_PURCHASE_REQUEST && resultCode == CANCELED_PURCHASE_RESPONSE)
            Toast.makeText(getContext(), getString(R.string.operation_canceled), Toast.LENGTH_LONG).show();
    }

    public static PurchaseFragment createNewInstance(){
        return new PurchaseFragment();
    }
}
